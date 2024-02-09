package com.leandroadal.vortasks.entities.social.dto.create;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;

public record GroupTaskCreateDTO(
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
        List<String> usernames) {

}
