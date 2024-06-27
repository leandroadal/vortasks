package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.time.Instant;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record MissionDTO(
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
        boolean finish, 
        Instant dateFinish,
        List<SkillDTO> skills,
        List<MissionTaskDTO> requirements) {

    public MissionDTO(Mission mission) {
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
        mission.isFinish(),
        mission.getDateFinish(),
        mission.getSkills().stream().map(SkillDTO::new).toList(),
        mission.getRequirements().stream().map(MissionTaskDTO::new).toList());
    }

    public Mission toMission(Backup backup) {
        return new Mission(
            id,
            title,
            description,
            status,
            xp,
            coins,
            type,
            repetition,
            reminder,
            skillIncrease,
            skillDecrease,
            startDate,
            endDate,
            theme,
            difficulty,
            finish,
            dateFinish,
            skills.stream().map(skillDTO -> skillDTO.toSkill(backup)).toList(),
            backup,
            requirements.stream().map(missionTaskDTO -> missionTaskDTO.toMissionTasks(backup)).toList()
        );
    }
}
