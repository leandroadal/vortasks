package com.leandroadal.vortasks.entities.backup.dto;

import java.time.Instant;
import java.util.List;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.MissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.TaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;

public record BackupRequestDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        Instant lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {

    public Backup toBackup(Backup backup) {
        // User user = new User();
        // user.setId(userId);
        backup.setLastModified(this.lastModified);
        mapCheckInDays(this.checkInDays, backup);
        mapGoals(this.goals, backup);
        mapAchievements(this.achievements, backup);
        mapMissions(this.missions, backup);
        mapTasks(this.tasks, backup);
        mapSkills(this.skills, backup);
        return backup;
    }

    private void mapCheckInDays(CheckInDaysDTO data, Backup backup) {
        backup.setCheckInDays(data.toCheckInDays(backup));
    }

    private void mapGoals(GoalsDTO data, Backup backup) {
        backup.setGoals(data.toGoals(backup));
    }

    private void mapAchievements(List<AchievementDTO> dataList, Backup backup) {
        backup.setAchievements(dataList.stream().map(ach -> ach.toAchievement(backup)).toList());
    }

    private void mapMissions(List<MissionDTO> dataList, Backup userBackup) {
        userBackup.setMissions(dataList.stream().map(missionDTO -> missionDTO.toMission(userBackup)).toList());
    }

    private void mapTasks(List<TaskDTO> dataList, Backup userBackup) {
        userBackup.setTasks(dataList.stream().map(taskDTO -> taskDTO.toTask(userBackup)).toList());
    }

    private void mapSkills(List<SkillDTO> dataList, Backup userBackup) {
        userBackup.setSkills(dataList.stream().map(skillDTO -> skillDTO.toSkill(userBackup)).toList());
    }
}