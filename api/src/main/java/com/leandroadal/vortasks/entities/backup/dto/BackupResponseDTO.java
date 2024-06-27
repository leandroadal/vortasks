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

public record BackupResponseDTO(
        String id,
        String userId,
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        Instant lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {

    public BackupResponseDTO(Backup userBackup) {
        this(
                userBackup.getId(),
                userBackup.getUser().getId(),
                new CheckInDaysDTO(userBackup.getCheckInDays()),
                new GoalsDTO(userBackup.getGoals()),
                userBackup.getLastModified(),
                userBackup.getAchievements().stream().map(AchievementDTO::new).toList(),
                userBackup.getTasks().stream().map(TaskDTO::new).toList(),
                userBackup.getMissions().stream().map(MissionDTO::new).toList(),
                userBackup.getSkills().stream().map(SkillDTO::new).toList());
    }

}
