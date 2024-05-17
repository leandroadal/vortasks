package com.leandroadal.vortasks.entities.social.tasks.dto.grouptask;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;

public record GroupTaskResponseDTO(
        String id,
        Status status,
        String name,
        String description,
        int xp,
        int coins,
        Type type,
        int repetition,
        Instant reminder,
        int skillIncrease,
        int skillDecrease,
        String author,
        String editor,
        Set<String> usernames) {

            
    public GroupTaskResponseDTO(GroupTask groupTask) {
        this(
            groupTask.getId(),
            groupTask.getStatus(),
            groupTask.getTitle(),
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
            groupTask.getUsers().stream().map(user -> user.getUsername()).collect(Collectors.toSet()));
    }

}
