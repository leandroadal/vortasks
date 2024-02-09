package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;

public record AbstractTaskCreateDTO(
        Status status,
        String name,
        String description,
        int xp,
        int coins,
        Type type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease) {

}
