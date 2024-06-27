package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;

public record AchievementDTO(String id, String title, String description, int xp) {

    public AchievementDTO(Achievement achievement) {
        this(achievement.getId(), achievement.getTitle(), achievement.getDescription(), achievement.getXp());
    }

    public Achievement toAchievement(Backup backup) {
        return new Achievement(
            this.id,
            title,
            description,
            xp,
            backup
        );
        
    }
}
