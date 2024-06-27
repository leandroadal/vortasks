package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMissionTasks;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record MissionTaskCreateDTO(
        Status status,
        String title,
        String description,
        int xp,
        int coins,
        Type type,
        int repetition,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant reminder,

        int skillIncrease,
        int skillDecrease,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant startDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant endDate,

        Theme theme,
        Difficulty difficulty,
        boolean finish, 

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant dateFinish,

        List<SkillCreateDTO> skills
) {

    public MissionTasks toMissionTasks(Backup backup) {
        MissionTasks missionTasks = new MissionTasks();
        missionTasks.setTitle(title);
        missionTasks.setDescription(description);
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
        missionTasks.getSkills().addAll(skills.stream().map(skillCreateDTO -> skillCreateDTO.toSkill(backup)).toList());
        missionTasks.setFinish(finish);
        missionTasks.setDateFinish(dateFinish);
        missionTasks.setStatus(status);
        return missionTasks;
    }

    public OnlineMissionTasks toOnlineMissionTasks() {
        OnlineMissionTasks missionTasks = new OnlineMissionTasks();
        missionTasks.setTitle(title);
        missionTasks.setDescription(description);
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
        missionTasks.getSkills().addAll(skills.stream().map(skillCreateDTO -> skillCreateDTO.toSkill(null)).toList());
        return missionTasks;
    }

}
