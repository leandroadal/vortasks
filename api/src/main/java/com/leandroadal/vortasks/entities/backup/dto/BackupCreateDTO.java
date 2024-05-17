package com.leandroadal.vortasks.entities.backup.dto;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.MissionCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.TaskCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AchievementCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.CheckInDaysCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.GoalsCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.SkillCreateDTO;
import com.leandroadal.vortasks.entities.user.User;

public record BackupCreateDTO(
        CheckInDaysCreateDTO checkInDays,
        GoalsCreateDTO goals,
        Instant lastModified,
        List<AchievementCreateDTO> achievements,
        List<TaskCreateDTO> tasks,
        List<MissionCreateDTO> missions,
        List<SkillCreateDTO> skills) {

    public Backup toBackup(Backup backup) {
        User user = new User();
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
        backup.setGoals(new Goals(goalsDTO.daily(), goalsDTO.monthly(), goalsDTO.dailyGoalProgress(), goalsDTO.monthlyGoalProgress(), backup));
    }

    private void mapAchievements(List<AchievementCreateDTO> achievementDTOList, Backup backup) {
        backup.setAchievements(
                mapList(achievementDTOList,
                        achievementDTO -> new Achievement(achievementDTO.title(),
                                achievementDTO.description(), achievementDTO.xp(), backup)));
    }

    private void mapMissions(List<MissionCreateDTO> missionDTOList, Backup userBackup) {
        userBackup.setMissions(mapList(missionDTOList, missionDTO -> {
            Mission mission = new Mission(null, missionDTO.title(),
                    missionDTO.description(), missionDTO.status(), missionDTO.xp(), missionDTO.coins(),
                    missionDTO.type(), missionDTO.repetition(), missionDTO.reminder(), missionDTO.skillIncrease(),
                    missionDTO.skillDecrease(), missionDTO.startDate(), missionDTO.endDate(), missionDTO.theme(), missionDTO.difficulty(), userBackup, null);
            mission.setRequirements(
                    mapList(missionDTO.requirements(),
                            taskDTO -> new MissionTasks(null, taskDTO.title(),
                                    taskDTO.description(), taskDTO.status(), taskDTO.xp(), taskDTO.coins(),
                                    taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(), taskDTO.skillIncrease(),
                                    taskDTO.skillDecrease(), taskDTO.startDate(), taskDTO.endDate(), taskDTO.theme(), taskDTO.difficulty(), mission)));
            return mission;
        }));

    }

    private void mapTasks(List<TaskCreateDTO> taskDTOList, Backup userBackup) {
        userBackup.setTasks(mapList(taskDTOList,
                taskDTO -> new Task(null, taskDTO.title(), taskDTO.description(), taskDTO.status(), taskDTO.xp(),
                        taskDTO.coins(), taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(),
                        taskDTO.skillIncrease(), taskDTO.skillDecrease(), taskDTO.startDate(), taskDTO.endDate(), taskDTO.theme(), taskDTO.difficulty(),
                        userBackup)));
    }

    private void mapSkills(List<SkillCreateDTO> skillDTOList, Backup userBackup) {
        userBackup.setSkills(mapList(skillDTOList,
                skillDTO -> new Skill(null, skillDTO.name(), skillDTO.xp(), skillDTO.level(), userBackup)));
    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).toList();
    }
}
