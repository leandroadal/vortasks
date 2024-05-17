package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.userprogress.Goals;

public record GoalsDTO(String id, float daily, float monthly, int dailyGoalProgress,
int monthlyGoalProgress) {

    public GoalsDTO(Goals goals) {
        this(goals.getId(), goals.getDaily(), goals.getMonthly(), goals.getDailyGoalProgress(),
        goals.getMonthlyGoalProgress());
    }
}