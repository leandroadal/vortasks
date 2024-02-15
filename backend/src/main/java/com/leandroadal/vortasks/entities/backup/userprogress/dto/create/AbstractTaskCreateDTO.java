package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

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
        int skillDecrease,
        Instant startDate,
        Instant endDate,
        Theme theme,
        Difficulty difficulty) {

                
}
