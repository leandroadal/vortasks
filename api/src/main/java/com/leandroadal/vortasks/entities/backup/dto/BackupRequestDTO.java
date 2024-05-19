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
import com.leandroadal.vortasks.entities.backup.userprogress.dto.MissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.TaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.validation.constraints.NotEmpty;

public record BackupRequestDTO(
        @NotEmpty String userId,
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        Instant lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {

    public Backup toBackup(Backup backup) {
        User user = new User();
        user.setId(userId);
        backup.setLastModified(this.lastModified);
        mapCheckInDays(this.checkInDays, backup);
        mapGoals(goals, backup);
        mapAchievements(this.achievements, backup);
        mapMissions(missions, backup);
        mapTasks(tasks, backup);
        mapSkills(skills, backup);
        return backup;
    }

    private void mapCheckInDays(CheckInDaysDTO data, Backup backup) {
        backup.setCheckInDays(
                new CheckInDays(data.id(), data.days(), data.month(),
                        backup));
    }

    private void mapGoals(GoalsDTO data, Backup backup) {
        backup.setGoals(new Goals(data.id(), data.daily(), data.monthly(), data.dailyGoalProgress(), data.monthlyGoalProgress(), backup));
    }

    private void mapAchievements(List<AchievementDTO> dataList, Backup backup) {
        backup.setAchievements(
                mapList(dataList,
                        achDTO -> new Achievement(achDTO.id(), achDTO.title(),
                                achDTO.description(), achDTO.xp(), backup)));
    }

    private void mapMissions(List<MissionDTO> dataList, Backup userBackup) {
        userBackup.setMissions(mapList(dataList, missionDTO -> {
            Mission mission = new Mission(missionDTO.id(), missionDTO.title(),
                    missionDTO.description(), missionDTO.status(), missionDTO.xp(), missionDTO.coins(),
                    missionDTO.type(), missionDTO.repetition(), missionDTO.reminder(), missionDTO.skillIncrease(),
                    missionDTO.skillDecrease(), missionDTO.startDate(), missionDTO.endDate(), missionDTO.theme(), missionDTO.difficulty(), missionDTO.finish(), missionDTO.dateFinish(), userBackup, null);
            mission.setRequirements(
                    mapList(missionDTO.requirements(),
                            taskDTO -> new MissionTasks(taskDTO.id(), taskDTO.title(),
                                    taskDTO.description(), taskDTO.status(), taskDTO.xp(), taskDTO.coins(),
                                    taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(), taskDTO.skillIncrease(),
                                    taskDTO.skillDecrease(), taskDTO.startDate(), taskDTO.endDate(), taskDTO.theme(), taskDTO.difficulty(), missionDTO.finish(), missionDTO.dateFinish(),
                                    mission)));
            return mission;
        }));

    }

    private void mapTasks(List<TaskDTO> dataList, Backup userBackup) {
        userBackup.setTasks(mapList(dataList,
                taskDTO -> new Task(taskDTO.id(), taskDTO.title(), taskDTO.description(), taskDTO.status(), taskDTO.xp(),
                        taskDTO.coins(), taskDTO.type(), taskDTO.repetition(), taskDTO.reminder(),
                        taskDTO.skillIncrease(), taskDTO.skillDecrease(), taskDTO.startDate(), taskDTO.endDate(), taskDTO.theme(), taskDTO.difficulty(), taskDTO.finish(), taskDTO.dateFinish(), userBackup)));
    }

    private void mapSkills(List<SkillDTO> dataList, Backup userBackup) {
        userBackup.setSkills(mapList(dataList,
                skillDTO -> new Skill(null, skillDTO.name(), skillDTO.xp(), skillDTO.level(), userBackup)));
    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).toList();
    }

}