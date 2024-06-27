package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record MissionCreateDTO( 
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
        
        List<MissionTaskCreateDTO> requirements) {

    public Mission toMission(Backup backup) {
        Mission mission = new Mission();
        mission.setTitle(title);
        mission.setDescription(description);
        mission.setXp(xp);
        mission.setCoins(coins);
        mission.setType(type);
        mission.setRepetition(repetition);
        mission.setReminder(reminder);
        mission.setSkillIncrease(skillIncrease);
        mission.setSkillDecrease(skillDecrease);
        mission.setStartDate(startDate);
        mission.setEndDate(endDate);
        mission.setTheme(theme);
        mission.setDifficulty(difficulty);
        mission.setFinish(finish);
        mission.setDateFinish(dateFinish);
        mission.getRequirements().addAll(requirements.stream().map(missionTaskCreateDTO -> missionTaskCreateDTO.toMissionTasks(backup)).toList());
        mission.setStatus(status);
        mission.setUserBackup(backup);
        return mission;
    }
}
