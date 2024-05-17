package com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.response;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;

public record OnlineMissionResponseDTO(
        String id,
        Status status,
        String title,
        String description,
        Integer xp,
        Integer coins,
        Type type,
        Integer repetition,
        Instant reminder,
        Integer skillIncrease,
        Integer skillDecrease,
        Instant startDate,
        Instant endDate,
        Set<OnlineMissionTasksResponseDTO> requirements,
        Integer likes) {

    public OnlineMissionResponseDTO(OnlineMission onlineMission) {
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
            onlineMission.getStartDate(), 
            onlineMission.getEndDate(),
            onlineMission.getRequirements().stream()
                                            .map(OnlineMissionTasksResponseDTO::new)
                                            .collect(Collectors.toSet()), 
            onlineMission.getLikes());
    }

}
