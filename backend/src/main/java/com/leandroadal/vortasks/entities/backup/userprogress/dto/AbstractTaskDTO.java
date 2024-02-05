package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.social.OnlineMissionTasks;

public record AbstractTaskDTO(
        String id, 
        String status,
        String name,
        String description,
        int xp,
        int coins,
        String type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease) {

    public AbstractTaskDTO(Task task) {
        this(
            task.getId(),
            task.getStatus(),
            task.getName(),
            task.getDescription(),
            task.getXp(),
            task.getCoins(),
            task.getType(),
            task.getRepetition(),
            task.getReminder(),
            task.getSkillIncrease(),
            task.getSkillDecrease());
    }

    public AbstractTaskDTO(MissionTasks data) {
        this(
            data.getId(),
            data.getStatus(),
            data.getName(),
            data.getDescription(),
            data.getXp(),
            data.getCoins(),
            data.getType(),
            data.getRepetition(),
            data.getReminder(),
            data.getSkillIncrease(),
            data.getSkillDecrease()
        );
    }

    public AbstractTaskDTO(OnlineMissionTasks data) {
        this(
            data.getId(),
            data.getStatus(),
            data.getName(),
            data.getDescription(),
            data.getXp(),
            data.getCoins(),
            data.getType(),
            data.getRepetition(),
            data.getReminder(),
            data.getSkillIncrease(),
            data.getSkillDecrease());
    }
}
