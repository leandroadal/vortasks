package com.leandroadal.vortasks.dto;

public record TaskDTO(
        String status,
        String name,
        String description,
        int xp,
        float coins,
        String type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease) {

}
