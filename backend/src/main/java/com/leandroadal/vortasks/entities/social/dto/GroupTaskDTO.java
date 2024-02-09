package com.leandroadal.vortasks.entities.social.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.GroupTask;

public record GroupTaskDTO(
        String id,
        Status status,
        String name,
        String description,
        int xp,
        int coins,
        Type type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        String author,
        String editor,
        Set<String> usernames) {

    public GroupTaskDTO(GroupTask groupTask) {
        this(
            groupTask.getId(),
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
            groupTask.getUser().stream().map(user -> user.getUsername()).collect(Collectors.toSet()));
    }
}
