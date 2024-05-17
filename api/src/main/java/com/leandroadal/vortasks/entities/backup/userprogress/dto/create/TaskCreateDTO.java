package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record TaskCreateDTO(
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

                
}
