package com.leandroadal.vortasks.entities.backup.dto;

import java.time.Instant;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.leandroadal.vortasks.entities.backup.Backup;
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant lastModified,
        
        List<AchievementCreateDTO> achievements,
        List<TaskCreateDTO> tasks,
        List<MissionCreateDTO> missions,
        List<SkillCreateDTO> skills) {

    public Backup toBackup(Backup backup) {
        User user = new User();
        backup.setUser(user);
        backup.setLastModified(this.lastModified);
        mapCheckInDays(checkInDays, backup);;
        mapGoals(goals, backup);
        mapAchievements(this.achievements, backup);
        mapMissions(this.missions, backup);
        mapTasks(this.tasks, backup);
        mapSkills(this.skills, backup);
        return backup;
    }

    private void mapCheckInDays(CheckInDaysCreateDTO checkInDaysDTO, Backup backup) {
        backup.setCheckInDays(checkInDaysDTO.toCheckInDays(backup));
    }

    private void mapGoals(GoalsCreateDTO goalsDTO, Backup backup) {
        backup.setGoals(goalsDTO.toGoals(backup));
    }

    private void mapAchievements(List<AchievementCreateDTO> achievementDTOList, Backup backup) {
        backup.setAchievements(this.achievements.stream().map(achCreateDTO -> achCreateDTO.toAchievement(backup)).toList());                        
    }

    private void mapMissions(List<MissionCreateDTO> dataList, Backup backup) {
        backup.setMissions(this.missions.stream().map(missionDTO -> missionDTO.toMission(backup)).toList());
    }

    private void mapTasks(List<TaskCreateDTO> taskDTOList, Backup backup) {
        backup.setTasks(this.tasks.stream().map(taskDTO -> taskDTO.toTask(backup)).toList());
    }

    private void mapSkills(List<SkillCreateDTO> skillDTOList, Backup backup) {
        backup.setSkills(this.skills.stream().map(skillDTO -> skillDTO.toSkill(backup)).toList());
    }
}
