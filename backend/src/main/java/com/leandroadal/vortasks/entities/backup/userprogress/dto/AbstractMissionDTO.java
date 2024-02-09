package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.util.List;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;

public record AbstractMissionDTO(
        String id, 
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
        List<AbstractTaskDTO> requirements) {

    public AbstractMissionDTO(Mission mission) {
        this(
        mission.getId(),
        mission.getStatus(),
        mission.getTitle(),
        mission.getDescription(),
        mission.getXp(),
        mission.getCoins(),
        mission.getType(),
        mission.getRepetition(),
        mission.getReminder(),
        mission.getSkillIncrease(),
        mission.getSkillDecrease(),
        mission.getRequirements().stream().map(AbstractTaskDTO::new).toList());
    }
}
