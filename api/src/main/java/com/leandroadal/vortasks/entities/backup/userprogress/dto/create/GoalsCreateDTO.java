package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

public record GoalsCreateDTO(
        int daily,
        int weekly,
        int monthly,
        int dailyGoalProgress,
        int monthlyGoalProgress) {

}
