package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record AbstractTaskDTO(
        String id, 
        Status status,
        String title,
        String description,
        int xp,
        int coins,
        Type type,
        int repetition,
        Instant reminder,
        int skillIncrease,
        int skillDecrease,
        Instant startDate,
        Instant endDate,
        Theme theme,
        Difficulty difficulty) {

    public AbstractTaskDTO(Task task) {
        this(
            task.getId(),
            task.getStatus(),
            task.getTitle(),
            task.getDescription(),
            task.getXp(),
            task.getCoins(),
            task.getType(),
            task.getRepetition(),
            task.getReminder(),
            task.getSkillIncrease(),
            task.getSkillDecrease(),
            task.getStartDate(),
            task.getEndDate(),
            task.getTheme(),
            task.getDifficulty());
    }

}
