package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;

public record GoalsCreateDTO(
        int daily,
        int weekly,
        int monthly,
        int dailyGoalProgress,
        int weeklyGoalProgress,
        int monthlyGoalProgress) {

    public Goals toGoals(Backup backup) {
        Goals goals = new Goals();
        goals.setDaily(daily);
        goals.setWeekly(weekly);
        goals.setMonthly(monthly);
        goals.setDailyGoalProgress(dailyGoalProgress);
        goals.setWeeklyGoalProgress(weeklyGoalProgress);
        goals.setMonthlyGoalProgress(monthlyGoalProgress);
        goals.setUserBackup(backup);
        return goals;
    }
}
