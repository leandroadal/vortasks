package com.leandroadal.vortasks.dto;

import java.util.List;

public record UserBackupDTO(
        String username,
        int level,
        float xp,
        CheckInDaysDTO checkInDays,
        GoalsDTO goals,
        String lastModified,
        List<AchievementDTO> achievements,
        List<TaskDTO> tasks,
        List<MissionDTO> missions,
        List<SkillDTO> skills) {

}
