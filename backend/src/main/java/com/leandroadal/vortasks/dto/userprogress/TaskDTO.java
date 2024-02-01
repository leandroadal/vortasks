package com.leandroadal.vortasks.dto.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.Task;

public record TaskDTO(
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

    public TaskDTO(Task task) {
        this(
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
}
