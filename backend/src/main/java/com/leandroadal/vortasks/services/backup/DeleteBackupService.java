package com.leandroadal.vortasks.services.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeleteBackupService {

    @Autowired
    private BackupOperationService backupService;

    @Transactional
    public void deleteUserBackup(Long userId) {
        UserBackup userBackup = backupService.getBackupByUserId(userId);

        if (userBackup != null && userBackup.getId() != null) {
            removeReferences(userBackup);
            backupService.deleteBackup(userBackup);
            logBackupDeletionSuccess(userId);
        }
    }

    private void removeReferences(UserBackup userBackup) {
        removeUserProgressDataReference(userBackup);
        removeCheckInDaysReference(userBackup);
        removeGoalsReference(userBackup);
        removeAchievementsReference(userBackup);
        removeTasksReference(userBackup);
        removeMissionsReference(userBackup);
        removeSkillsReference(userBackup);
    }

    private void removeUserProgressDataReference(UserBackup userBackup) {
        if (userBackup.getProgressData() != null) {
            userBackup.getProgressData().setBackup(null);
        }
    }

    private void removeCheckInDaysReference(UserBackup userBackup) {
        if (userBackup.getCheckInDays() != null) {
            userBackup.getCheckInDays().setUserBackup(null);
        }
    }

    private void removeGoalsReference(UserBackup userBackup) {
        if (userBackup.getGoals() != null) {
            userBackup.getGoals().setUserBackup(null);
        }
    }

    private void removeAchievementsReference(UserBackup userBackup) {
        if (userBackup.getAchievements() != null) {
            userBackup.getAchievements().forEach(achievement -> achievement.setUserBackup(null));
        }
    }

    private void removeTasksReference(UserBackup userBackup) {
        if (userBackup.getTasks() != null) {
            userBackup.getTasks().forEach(task -> task.setUserBackup(null));
        }
    }

    private void removeMissionsReference(UserBackup userBackup) {
        if (userBackup.getMissions() != null) {
            userBackup.getMissions().forEach(mission -> {
                mission.setUserBackup(null);
                mission.getRequirements().forEach(requirement -> requirement.setMission(null));
            });
        }
    }

    private void removeSkillsReference(UserBackup userBackup) {
        if (userBackup.getSkills() != null) {
            userBackup.getSkills().forEach(skill -> skill.setUserBackup(null));
        }
    }

    private void logBackupDeletionSuccess(Long userId) {
        log.info("Backup excluído com sucesso para o usuário {}", userId);
    }
}
