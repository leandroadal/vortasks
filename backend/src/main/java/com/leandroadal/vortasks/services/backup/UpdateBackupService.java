package com.leandroadal.vortasks.services.backup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.dto.UserBackupDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.repositories.backup.AchievementRepository;
import com.leandroadal.vortasks.repositories.backup.MissionRepository;
import com.leandroadal.vortasks.repositories.backup.SkillRepository;
import com.leandroadal.vortasks.repositories.backup.TaskRepository;
import com.leandroadal.vortasks.services.backup.exceptions.AchievementNotFoundException;
import com.leandroadal.vortasks.services.backup.exceptions.MissionNotFoundException;
import com.leandroadal.vortasks.services.backup.exceptions.SkillNotFoundException;
import com.leandroadal.vortasks.services.backup.exceptions.TaskNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateBackupService {

    @Autowired
    private BackupOperationService backupService;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TaskRepository taskRepository;

    public void updateBackup(UserBackupDTO data, Long userId) {
        UserBackup backup = backupService.getBackupByUserId(userId);

        updateCheckInDays(data.checkInDays(), backup);
        updateGoals(data.goals(), backup);
        updateAchievements(data.achievements(), backup);
        updateMissions(data.missions(), backup);
        updateTasks(data.tasks(), backup);
        updateSkills(data.skills(), backup);

        backupService.saveBackup(backup);
        logBackupUpdateSuccess(userId);
    }

    private void updateCheckInDays(CheckInDaysDTO data, UserBackup backup) {
        backup.getCheckInDays().edit(data);
    }

    private void updateGoals(GoalsDTO data, UserBackup backup) {
        backup.getGoals().edit(data);
    }

    private void updateAchievements(List<AchievementDTO> achievementDTOList, UserBackup backup) {
        List<Achievement> achievements = new ArrayList<>();

        for (AchievementDTO achievementDTO : achievementDTOList) {
            Achievement achievement = achievementRepository.findById(achievementDTO.id())
                    .orElseThrow(() -> new AchievementNotFoundException(achievementDTO.id()));
            achievement.edit(achievementDTO);
            achievements.add(achievement);
        }

        backup.setAchievements(achievements);
    }

    private void updateMissions(List<AbstractMissionDTO> missionDTOList, UserBackup backup) {
        List<Mission> missions = new ArrayList<>();

        for (AbstractMissionDTO missionDTO : missionDTOList) {
            Mission mission = missionRepository.findById(missionDTO.id())
                    .orElseThrow(() -> new MissionNotFoundException(missionDTO.id()));
            mission.edit(missionDTO);
            missions.add(mission);
        }

        backup.setMissions(missions);
    }

    private void updateTasks(List<AbstractTaskDTO> taskDTOList, UserBackup backup) {
        List<Task> tasks = new ArrayList<>();

        for (AbstractTaskDTO taskDTO : taskDTOList) {
            Task task = taskRepository.findById(taskDTO.id())
                    .orElseThrow(() -> new TaskNotFoundException(taskDTO.id()));
            task.edit(taskDTO);
            tasks.add(task);
        }
        backup.setTasks(tasks);
    }

    private void updateSkills(List<SkillDTO> skillDTOList, UserBackup backup) {
        List<Skill> skills = new ArrayList<>();

        for (SkillDTO skillDTO : skillDTOList) {
            Skill skill = skillRepository.findById(skillDTO.id())
                    .orElseThrow(() -> new SkillNotFoundException(skillDTO.id()));
            skill.edit(skillDTO);
            skills.add(skill);
        }

        backup.setSkills(skills);
    }

    private void logBackupUpdateSuccess(Long userId) {
        log.info("Backup atualizado com sucesso para o usuário {}", userId);
    }

}
