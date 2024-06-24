package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;

public record GoalsDTO(String id, int daily, int weekly, int monthly, int dailyGoalProgress, int weeklyGoalProgress,
int monthlyGoalProgress) {

    public GoalsDTO(Goals goals) {
        this(goals.getId(), goals.getDaily(), goals.getWeekly(), goals.getMonthly(), goals.getDailyGoalProgress(),
        goals.getWeeklyGoalProgress(),
        goals.getMonthlyGoalProgress());
    }

    public Goals toGoals(Backup backup) {
        return new Goals(
            id,
            daily,
            weekly,
            monthly,
            dailyGoalProgress,
            weeklyGoalProgress,
            monthlyGoalProgress,
            backup
        );
    }
}
