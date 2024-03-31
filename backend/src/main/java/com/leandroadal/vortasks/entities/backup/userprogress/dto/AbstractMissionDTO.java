package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.time.Instant;
import java.util.List;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record AbstractMissionDTO(
        String id, 
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
        Difficulty difficulty, 
        List<MissionTaskDTO> requirements) {

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
        mission.getStartDate(),
        mission.getEndDate(),
        mission.getTheme(),
        mission.getDifficulty(),
        mission.getRequirements().stream().map(MissionTaskDTO::new).toList());
    }
}
