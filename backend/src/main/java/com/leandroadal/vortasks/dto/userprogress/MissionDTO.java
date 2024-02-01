package com.leandroadal.vortasks.dto.userprogress;

import java.util.List;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.userprogress.Mission;

public record MissionDTO(
        String status,
        String title,
        String description,
        int xp,
        int coins,
        String type,
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        List<TaskDTO> requirements) {

    public MissionDTO(Mission mission) {
        this(
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
        mission.getRequirements().stream().map(TaskDTO::new).collect(Collectors.toList()));
    }
}
