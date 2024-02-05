package com.leandroadal.vortasks.entities.social.dto;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
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
        List<AbstractTaskDTO> requirements,
        int likes) {

    public OnlineMissionDTO(OnlineMission onlineMission, List<AbstractTaskDTO> requirements) {
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
