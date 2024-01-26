package com.leandroadal.vortasks.dto.social;

import java.util.List;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.social.GroupTask;

public record GroupTaskDTO(
        String status,
        String name,
        String description,
        int xp,
        float coins,
        String type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        String author,
        String editor,
        String category,
        List<String> usernames) {

    public GroupTaskDTO(GroupTask groupTask) {
        this(
            groupTask.getStatus(),
            groupTask.getName(),
            groupTask.getDescription(),
            groupTask.getXp(),
            groupTask.getCoins(),
            groupTask.getType(),
            groupTask.getRepetition(),
            groupTask.getReminder(),
            groupTask.getSkillIncrease(),
            groupTask.getSkillDecrease(),
            groupTask.getAuthor(),
            groupTask.getEditor(),
            groupTask.getCategory(),
            groupTask.getProgressData().stream().map(user -> user.getUser().getUsername())
                    .collect(Collectors.toList()));
    }
}
