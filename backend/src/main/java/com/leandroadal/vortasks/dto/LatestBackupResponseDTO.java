package com.leandroadal.vortasks.dto;

import java.time.LocalDateTime;
import java.util.List;

public record LatestBackupResponseDTO(
        int level,
        float xp,
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        LocalDateTime lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {
}
