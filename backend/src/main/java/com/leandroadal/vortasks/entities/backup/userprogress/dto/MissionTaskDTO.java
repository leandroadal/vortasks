package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;


public record MissionTaskDTO(
    String id,
    String title,
    String description,
    Status status,
    int xp,
    Integer coins,
    Type type,
    String repetition,
    String reminder,
    Integer skillIncrease,
    Integer skillDecrease,
    Instant startDate,
    Instant endDate
) {
    public MissionTaskDTO(MissionTasks missionTask) {
        this(
            missionTask.getId(),
            missionTask.getTitle(),
            missionTask.getDescription(),
            missionTask.getStatus(),
            missionTask.getXp(),
            missionTask.getCoins(),
            missionTask.getType(),
            missionTask.getRepetition(),
            missionTask.getReminder(),
            missionTask.getSkillIncrease(),
            missionTask.getSkillDecrease(),
            missionTask.getStartDate(),
            missionTask.getEndDate()
        );
    }

}
