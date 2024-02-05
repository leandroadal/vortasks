package com.leandroadal.vortasks.entities.backup.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

public record LatestBackupResponseDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<AbstractTaskDTO> tasks,
        List<AbstractMissionDTO> missions,
        List<SkillDTO> skills) {
}
