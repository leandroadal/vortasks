package com.leandroadal.vortasks.services.backup;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.backup.BackupRepository;
import com.leandroadal.vortasks.security.UserSS;
import com.leandroadal.vortasks.services.backup.exceptions.BackupCreationException;
import com.leandroadal.vortasks.services.backup.exceptions.ObjectNotModifiedException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.user.UserService;

@Service
public class BackupService {

    @Autowired
    private UserService userService;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private BackupAssociationService backupAssociation;

    @Autowired
    private LogBackupService logService;

    public List<Backup> getAllBackups() {
        return backupRepository.findAll();
    }

    public Backup findBackup(String id) {
        try {
            return backupRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            logService.logFindBackup(id);
            throw e;
        }
    }

    public Page<Backup> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return backupRepository.findAll(pageRequest);
    }

    public Backup getBackupByUserId(String userId) {
        try {
            return backupRepository.findByUserId(userId)
                    .orElseThrow(() -> new ObjectNotFoundException(userId));
        } catch (ObjectNotFoundException e) {
            logService.logGetBackupByUserId(userId);
            throw e;
        }
    }

    public User findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    @Transactional
    public Backup createBackup(Backup backup) {
        UserSS userSS = UserService.authenticated();
        User user = userService.findUserById(userSS.getId());
        validateBackupCreation(user);
        try {
            backupAssociation.linkUserAndBackup(user, backup);
            saveBackup(backup);

            logService.logBackupCreationSuccess(user);
            return backup;
        } catch (Exception e) {
            logService.logBackupCreationFailure(user);
            throw new BackupCreationException("Falha ao criar o backup", e);
        }
    }

    public Backup latestBackup(Instant lastModified) {
        UserSS userSS = UserService.authenticated();
        Backup backup = getBackupByUserId(userSS.getId());

        if (lastModified == null || backup.getLastModified().isAfter(lastModified)) {
            logService.logLatestBackupRetrievalSuccess(backup.getId());
            return backup;
        } else {
            logService.logBackupNotModified(backup.getId());
            throw new ObjectNotModifiedException("Backup não modificado para o usuário", backup.getId());
        }
    }

    @Transactional
    public Backup updateBackup(Backup data) {
        UserSS userSS = UserService.authenticated();
        Backup newBackup = getBackupByUserId(userSS.getId());
        updateData(newBackup, data);
        saveBackup(newBackup);
        logService.logBackupUpdateSuccess(newBackup.getId());
        return newBackup;
    }

    @Transactional
    public void deleteUserBackup() {
        UserSS userSS = UserService.authenticated();
        Backup userBackup = getBackupByUserId(userSS.getId());

        backupAssociation.removeReferences(userBackup);
        deleteBackup(userBackup);
        logService.logBackupDeletionSuccess(userSS.getId());
    }

    private void validateBackupCreation(User user) {
        if (user.getBackup() != null) {
            throw new BackupCreationException("Backup já existe para o usuário com ID:", user.getId());
        }
    }

    private void updateData(Backup newBackup, Backup data) {
        newBackup.setLastModified(data.getLastModified());
        newBackup.setAchievements(data.getAchievements());
        newBackup.setCheckInDays(data.getCheckInDays());
        newBackup.setGoals(data.getGoals());
        newBackup.setMissions(data.getMissions());
        newBackup.setTasks(data.getTasks());
        newBackup.setSkills(data.getSkills());
    }

    private void saveBackup(Backup backup) {
        backupRepository.save(backup);
    }

    private void deleteBackup(Backup backup) {
        backupRepository.delete(backup);
    }
}
