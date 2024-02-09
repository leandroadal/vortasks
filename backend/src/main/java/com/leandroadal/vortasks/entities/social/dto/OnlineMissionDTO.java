package com.leandroadal.vortasks.entities.social.dto;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
import com.leandroadal.vortasks.entities.social.OnlineMission;

public record OnlineMissionDTO(
        String id,
        Status status,
        String title,
        String description,
        int xp,
        int coins,
        Type type,
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        List<AbstractTaskDTO> requirements,
        int likes) {

    public OnlineMissionDTO(OnlineMission onlineMission, List<AbstractTaskDTO> requirements) {
        this(
            onlineMission.getId(),
            onlineMission.getStatus(),
            onlineMission.getTitle(), 
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
