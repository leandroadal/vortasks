package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.time.Instant;

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
        String repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        Instant startDate,
        Instant endDate,
        Theme theme,
        Difficulty difficulty
) {

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
        return missionTasks;
    }

}
