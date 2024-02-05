package com.leandroadal.vortasks.services.backup;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.dto.UserBackupDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.services.backup.exceptions.BackupCreationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateBackupService {

    @Autowired
    private BackupOperationService backupService;

    public UserBackup createBackup(UserBackupDTO backupDTO, User user) {
        validateBackupCreation(user);

        try {
            UserBackup backup = new UserBackup(backupDTO.lastModified());
            mapBackupData(backupDTO, backup);
            linkUserAndBackup(user, backup);
            backupService.saveBackup(backup);
            
            logBackupCreationSuccess(user);
            return backup;
        } catch (Exception e) {
            logBackupCreationFailure(user, e);
            throw new BackupCreationException("Falha ao criar o backup", e);
        }
    }

    private void validateBackupCreation(User user) {
        if (user.getProgressData().getBackup() != null) {
            throw new BackupCreationException("Backup já existe para o usuário", user.getId());
        }
    }

    private void mapBackupData(UserBackupDTO backupDTO, UserBackup backup) {
        mapCheckInDays(backupDTO.checkInDays(), backup);
        mapGoals(backupDTO.goals(), backup);
        mapAchievements(backupDTO.achievements(), backup);
        mapMissions(backupDTO.missions(), backup);
        mapTasks(backupDTO.tasks(), backup);
        mapSkills(backupDTO.skills(), backup);
    }

    private void mapCheckInDays(CheckInDaysDTO checkInDaysDTO, UserBackup userBackup) {
        userBackup.setCheckInDays(new CheckInDays(checkInDaysDTO, userBackup));
    }

    private void mapGoals(GoalsDTO goalsDTO, UserBackup userBackup) {
        userBackup.setGoals(new Goals(goalsDTO, userBackup));
    }

    private void mapAchievements(List<AchievementDTO> achievementDTOList, UserBackup backup) {
        backup.setAchievements(
                mapList(achievementDTOList,
                        achievementDTO -> new Achievement(achievementDTO, backup)));
    }

    private void mapMissions(List<AbstractMissionDTO> missionDTOList, UserBackup userBackup) {
        userBackup.setMissions(mapList(missionDTOList, missionDTO -> {
            Mission mission = new Mission(missionDTO, userBackup);
            mission.setRequirements(
                    mapList(missionDTO.requirements(),
                            taskDTO -> new MissionTasks(taskDTO, mission)));
            return mission;
        }));

    }

    private void mapTasks(List<AbstractTaskDTO> taskDTOList, UserBackup userBackup) {
        userBackup.setTasks(mapList(taskDTOList,
                taskDTO -> new Task(taskDTO, userBackup)));
    }

    private void mapSkills(List<SkillDTO> skillDTOList, UserBackup userBackup) {
        userBackup.setSkills(mapList(skillDTOList,
                skillDTO -> new Skill(skillDTO, userBackup)));

    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).toList();
    }

    private void linkUserAndBackup(User user, UserBackup userBackup) {
        UserProgressData userProgressData = user.getProgressData();
        userProgressData.setBackup(userBackup);
        userBackup.setProgressData(userProgressData);
    }

    private void logBackupCreationFailure(User user, Exception e) {
        log.error("Erro ao criar o backup para o usuário '{}' com o username '{}'", user.getId(),
                user.getUsername(), e);
    }

    private void logBackupCreationSuccess(User user) {
        log.info("Backup criado com sucesso para o usuário '{}' com o username '{}'",
                user.getId(), user.getUsername());
    }
}
