package com.leandroadal.vortasks.entities.backup.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

public record UserBackupDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<AbstractTaskDTO> tasks,
        List<AbstractMissionDTO> missions,
        List<SkillDTO> skills) {

    public UserBackupDTO(UserBackup userBackup) {
        this(
            new CheckInDaysDTO(userBackup.getCheckInDays()),
            new GoalsDTO(userBackup.getGoals()),
            userBackup.getLastModified(),
            userBackup.getAchievements().stream().map(AchievementDTO::new).collect(Collectors.toList()),
            userBackup.getTasks().stream().map(AbstractTaskDTO::new).collect(Collectors.toList()),
            userBackup.getMissions().stream().map(AbstractMissionDTO::new).collect(Collectors.toList()),
            userBackup.getSkills().stream().map(SkillDTO::new).collect(Collectors.toList()));
    }

}
