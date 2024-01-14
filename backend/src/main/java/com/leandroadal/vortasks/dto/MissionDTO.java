package com.leandroadal.vortasks.dto;

import java.util.List;

public record MissionDTO(
        String status,
        String title,
        String description,
        int xp,
        float coins,
        String type,
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        List<TaskDTO> requirements) {

}
