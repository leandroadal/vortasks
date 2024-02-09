package com.leandroadal.vortasks.entities.backup.dto;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

import org.hibernate.validator.constraints.UUID;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractMissionCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractTaskCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AchievementCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.CheckInDaysCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.GoalsCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.SkillCreateDTO;
import com.leandroadal.vortasks.entities.user.User;

public record BackupCreateDTO(
        @UUID
        String userId,
        CheckInDaysCreateDTO checkInDays,
        GoalsCreateDTO goals,
        Instant lastModified,
        List<AchievementCreateDTO> achievements,
        List<AbstractTaskCreateDTO> tasks,
        List<AbstractMissionCreateDTO> missions,
        List<SkillCreateDTO> skills) {

    public Backup toBackup(Backup backup) {
        User user = new User();
        user.setId(userId);
        backup.setUser(user);
        backup.setLastModified(this.lastModified);
        mapCheckInDays(this.checkInDays, backup);
        mapGoals(goals, backup);
        mapAchievements(this.achievements, backup);
        mapMissions(missions, backup);
        mapTasks(tasks, backup);
        mapSkills(skills, backup);
        return backup;
    }

    private void mapCheckInDays(CheckInDaysCreateDTO checkInDaysDTO, Backup backup) {
        backup.setCheckInDays(
                new CheckInDays(checkInDaysDTO.days(), checkInDaysDTO.month(),
                        backup));
    }

    private void mapGoals(GoalsCreateDTO goalsDTO, Backup backup) {
        backup.setGoals(new Goals(goalsDTO.daily(), goalsDTO.monthly(), backup));
    }

    private void mapAchievements(List<AchievementCreateDTO> achievementDTOList, Backup backup) {
        backup.setAchievements(
                mapList(achievementDTOList,
                        achievementDTO -> new Achievement(achievementDTO.title(),
                                achievementDTO.description(), achievementDTO.xp(), backup)));
    }

    private void mapMissions(List<AbstractMissionCreateDTO> missionDTOList, Backup userBackup) {
        userBackup.setMissions(mapList(missionDTOList, missionDTO -> {
            Mission mission = new Mission(missionDTO.title(),
            missionDTO.description(), missionDTO.status(), missionDTO.xp(), missionDTO.coins(), missionDTO.type(), missionDTO.repetition(), missionDTO.reminder(), missionDTO.skillIncrease(), missionDTO.skillDecrease(), userBackup, null);
            mission.setRequirements(
                    mapList(missionDTO.requirements(),
                            taskDTO -> new MissionTasks(taskDTO.name(),
                            taskDTO.description(), taskDTO.status(), taskDTO.xp(), taskDTO.coins(), taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(), taskDTO.skillIncrease(), taskDTO.skillDecrease(), mission)));
            return mission;
        }));

    }

    private void mapTasks(List<AbstractTaskCreateDTO> taskDTOList, Backup userBackup) {
        userBackup.setTasks(mapList(taskDTOList,
                taskDTO -> new Task(taskDTO.name(), taskDTO.description(), taskDTO.status(), taskDTO.xp(), taskDTO.coins(), taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(), taskDTO.skillIncrease(), taskDTO.skillDecrease(), userBackup)));
    }

    private void mapSkills(List<SkillCreateDTO> skillDTOList, Backup userBackup) {
        userBackup.setSkills(mapList(skillDTOList,
                skillDTO -> new Skill(skillDTO.name(), skillDTO.xp(), skillDTO.level(), userBackup)));
    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).toList();
    }
}
