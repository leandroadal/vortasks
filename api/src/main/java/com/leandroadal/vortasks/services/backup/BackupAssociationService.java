package com.leandroadal.vortasks.services.backup;

import org.springframework.stereotype.Service;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.user.User;

@Service
public class BackupAssociationService {

    protected void linkUserAndBackup(User user, Backup userBackup) {
        user.setBackup(userBackup);
        userBackup.setUser(user);
    }

    protected void removeReferences(Backup userBackup) {
        removeUserReference(userBackup);
        removeCheckInDaysReference(userBackup);
        removeGoalsReference(userBackup);
        removeAchievementsReference(userBackup);
        removeTasksReference(userBackup);
        removeMissionsReference(userBackup);
        removeSkillsReference(userBackup);
    }

    private void removeUserReference(Backup userBackup) {
        if (userBackup.getUser() != null) {
            userBackup.getUser().setBackup(null);
        }
    }

    private void removeCheckInDaysReference(Backup userBackup) {
        if (userBackup.getCheckInDays() != null) {
            userBackup.getCheckInDays().setUserBackup(null);
        }
    }

    private void removeGoalsReference(Backup userBackup) {
        if (userBackup.getGoals() != null) {
            userBackup.getGoals().setUserBackup(null);
        }
    }

    private void removeAchievementsReference(Backup userBackup) {
        if (userBackup.getAchievements() != null) {
            userBackup.getAchievements().forEach(achievement -> achievement.setUserBackup(null));
        }
    }

    private void removeTasksReference(Backup userBackup) {
        if (userBackup.getTasks() != null) {
            userBackup.getTasks().forEach(task -> task.setUserBackup(null));
        }
    }

    private void removeMissionsReference(Backup userBackup) {
        if (userBackup.getMissions() != null) {
            userBackup.getMissions().forEach(mission -> {
                mission.setUserBackup(null);
                mission.getRequirements().forEach(requirement -> requirement.setMission(null));
            });
        }
    }

    private void removeSkillsReference(Backup userBackup) {
        if (userBackup.getSkills() != null) {
            userBackup.getSkills().forEach(skill -> skill.setUserBackup(null));
        }
    }

    
}
