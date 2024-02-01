package com.leandroadal.vortasks.dto.social;

import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.social.OnlineMission;

public record OnlineMissionDTO(
        String status,
        String title,
        String description,
        int xp,
        int coins,
        String type,
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        List<TaskDTO> requirements,
        int likes) {

    public OnlineMissionDTO(OnlineMission onlineMission, List<TaskDTO> requirements) {
        this(
            onlineMission.getStatus(),
            onlineMission.getStatus(), 
            onlineMission.getDescription(), 
            onlineMission.getXp(), 
            onlineMission.getCoins(), 
            onlineMission.getType(), 
            onlineMission.getRepetition(), 
            onlineMission.getReminder(), 
            onlineMission.getSkillIncrease(), 
            onlineMission.getSkillDecrease(), 
            requirements, 
            onlineMission.getLikes());
    }

}
