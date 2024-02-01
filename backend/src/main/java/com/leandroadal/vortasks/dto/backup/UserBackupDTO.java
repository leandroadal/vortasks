package com.leandroadal.vortasks.dto.backup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.dto.userprogress.AchievementDTO;
import com.leandroadal.vortasks.dto.userprogress.CheckInDaysDTO;
import com.leandroadal.vortasks.dto.userprogress.GoalsDTO;
import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.dto.userprogress.SkillDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

public record UserBackupDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {

    public UserBackupDTO(UserBackup userBackup) {
        this(
            new CheckInDaysDTO(userBackup.getCheckInDays()),
            new GoalsDTO(userBackup.getGoals()),
            userBackup.getLastModified(),
            userBackup.getAchievements().stream().map(AchievementDTO::new).collect(Collectors.toList()),
            userBackup.getTasks().stream().map(TaskDTO::new).collect(Collectors.toList()),
            userBackup.getMissions().stream().map(MissionDTO::new).collect(Collectors.toList()),
            userBackup.getSkills().stream().map(SkillDTO::new).collect(Collectors.toList()));
    }

}
