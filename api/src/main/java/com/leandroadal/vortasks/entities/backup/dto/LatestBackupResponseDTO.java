package com.leandroadal.vortasks.entities.backup.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.MissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.TaskDTO;

public record LatestBackupResponseDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {
}
