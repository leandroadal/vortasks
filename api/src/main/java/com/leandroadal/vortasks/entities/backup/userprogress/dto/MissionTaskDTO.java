package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.time.Instant;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;


public record MissionTaskDTO(
    String id,
    String title,
    String description,
    Status status,
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
    List<SkillDTO> skills
) {
    public MissionTaskDTO(MissionTasks missionTask) {
        this(
            missionTask.getId(),
            missionTask.getTitle(),
            missionTask.getDescription(),
            missionTask.getStatus(),
            missionTask.getXp(),
            missionTask.getCoins(),
            missionTask.getType(),
            missionTask.getRepetition(),
            missionTask.getReminder(),
            missionTask.getSkillIncrease(),
            missionTask.getSkillDecrease(),
            missionTask.getStartDate(),
            missionTask.getEndDate(),
            missionTask.getTheme(),
            missionTask.getDifficulty(),
            missionTask.isFinish(),
            missionTask.getDateFinish(),
            missionTask.getSkills().stream().map(SkillDTO::new).toList()
        );
    }

    public MissionTasks toMissionTasks(Backup backup) {
        MissionTasks missionTasks = new MissionTasks();
        missionTasks.setId(id);
        missionTasks.setTitle(title);
        missionTasks.setDescription(description);
        missionTasks.setStatus(status);
        missionTasks.setXp(xp);
        missionTasks.setCoins(coins);
        missionTasks.setType(type);
        missionTasks.setRepetition(repetition);
        missionTasks.setReminder(reminder);
        missionTasks.setSkillIncrease(skillIncrease);
        missionTasks.setSkillDecrease(skillDecrease);
        missionTasks.setStartDate(startDate);
        missionTasks.setEndDate(endDate);
        missionTasks.setTheme(theme);
        missionTasks.setDifficulty(difficulty);
        missionTasks.setFinish(finish);
        missionTasks.setDateFinish(dateFinish);
        missionTasks.setSkills(skills.stream().map(skillDTO -> skillDTO.toSkill(backup)).toList());
        return missionTasks;
    }

}
