package com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.response;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMissionTasks;

public record OnlineMissionTasksResponseDTO(
    String id,
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
    Instant endDate
) {

    public OnlineMissionTasksResponseDTO(OnlineMissionTasks mission) {
        this(
            mission.getId(),
            mission.getTitle(),
            mission.getDescription(),
            mission.getXp(),
            mission.getCoins(),
            mission.getType(),
            mission.getRepetition(),
            mission.getReminder(),
            mission.getSkillIncrease(),
            mission.getSkillDecrease(),
            mission.getStartDate(),
            mission.getEndDate()
        );
        
    }

}
