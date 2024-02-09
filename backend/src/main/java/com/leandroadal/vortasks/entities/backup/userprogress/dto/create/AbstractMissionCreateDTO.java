package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;

public record AbstractMissionCreateDTO( 
        Status status,
        String title,
        String description,
        int xp,
        int coins,
        Type type,
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        List<AbstractTaskCreateDTO> requirements) {

}
