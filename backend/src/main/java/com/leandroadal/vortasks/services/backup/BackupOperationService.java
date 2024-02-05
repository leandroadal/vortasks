package com.leandroadal.vortasks.services.backup;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.repositories.backup.UserBackupRepository;
import com.leandroadal.vortasks.repositories.user.UserProgressDataRepository;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.auth.exceptions.UserNotFoundException;
import com.leandroadal.vortasks.services.backup.exceptions.BackupNotFoundException;
import com.leandroadal.vortasks.services.backup.exceptions.ProgressDataNotFoundException;

@Service
public class BackupOperationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBackupRepository userBackupRepository;

    @Autowired
    private UserProgressDataRepository userProgressDataRepository;

    public void saveBackup(UserBackup backup) {
        userBackupRepository.save(backup);
    }
    
    public void deleteBackup(UserBackup backup) {
        userBackupRepository.delete(backup);
    }

    
    public List<UserBackup> getAllBackups() {
        return userBackupRepository.findAll();
    }

    public UserBackup getBackupByUserId(Long userId) {
        Long progressDataId = getProgressDataByUserId(userId).getId();
        return userBackupRepository.findByProgressDataId(progressDataId)
                .orElseThrow(() -> new BackupNotFoundException(userId));
    }

    public UserProgressData getProgressDataByUserId(Long userId) {
        return userProgressDataRepository.findByUserId(userId)
                .orElseThrow(() -> new ProgressDataNotFoundException(userId));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Usuário não encontrado o username: {}", username)));
    }

}
