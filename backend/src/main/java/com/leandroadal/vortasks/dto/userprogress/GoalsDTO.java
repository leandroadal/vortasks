package com.leandroadal.vortasks.dto.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.Goals;

public record GoalsDTO(float daily, float monthly) {

    public GoalsDTO(Goals goals) {
        this(goals.getDaily(), goals.getMonthly());
    }
}
