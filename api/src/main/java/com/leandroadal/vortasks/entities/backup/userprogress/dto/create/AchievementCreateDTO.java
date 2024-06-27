package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;

public record AchievementCreateDTO(
        String title,
        String description,
        int xp) {
    
    public Achievement toAchievement(Backup backup) {
        Achievement ach = new Achievement();
        ach.setTitle(title);
        ach.setDescription(description);
        ach.setXp(xp);
        ach.setUserBackup(backup);
        return ach;
    }
}
