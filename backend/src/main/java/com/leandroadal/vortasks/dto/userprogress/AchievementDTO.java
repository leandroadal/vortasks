package com.leandroadal.vortasks.dto.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;

public record AchievementDTO(String title, String description, int xp) {

    public AchievementDTO(Achievement achievement) {
        this(achievement.getTitle(), achievement.getDescription(), achievement.getXp());
    }
}
