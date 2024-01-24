package com.leandroadal.vortasks.dto.backup;

import java.time.LocalDateTime;
import java.util.List;

import com.leandroadal.vortasks.dto.shop.CheckInDaysDTO;
import com.leandroadal.vortasks.dto.userprogress.AchievementDTO;
import com.leandroadal.vortasks.dto.userprogress.GoalsDTO;
import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.dto.userprogress.SkillDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;

public record LatestBackupResponseDTO(
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {
}
