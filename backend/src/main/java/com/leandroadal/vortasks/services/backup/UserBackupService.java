package com.leandroadal.vortasks.services.backup;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.backup.UserBackupDTO;
import com.leandroadal.vortasks.dto.shop.CheckInDaysDTO;
import com.leandroadal.vortasks.dto.userprogress.AchievementDTO;
import com.leandroadal.vortasks.dto.userprogress.GoalsDTO;
import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.dto.userprogress.SkillDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.repositories.UserBackupRepository;

import jakarta.transaction.Transactional;

@Service
public class UserBackupService {

    @Autowired
    private UserBackupRepository userBackupRepository;

    @Transactional
    public UserBackup createBackup(UserBackupDTO userBackupDTO, User account) {
        UserBackup userBackup = new UserBackup(userBackupDTO.lastModified());

        mapCheckInDays(userBackupDTO.checkInDays(), userBackup);
        mapGoals(userBackupDTO.goals(), userBackup);
        mapAchievements(userBackupDTO.achievements(), userBackup);
        mapMissions(userBackupDTO.missions(), userBackup);
        mapTasks(userBackupDTO.tasks(), userBackup);
        mapSkills(userBackupDTO.skills(), userBackup);

        UserProgressData user = account.getProgressData();
        user.setBackup(userBackup);
        userBackup.setProgressData(user);

        return userBackupRepository.save(userBackup);
    }

    public UserBackupDTO latestBackup(UserBackup backup) {
        return new UserBackupDTO(backup);
    }

    public UserBackup getBackupByUserId(Long accountId) {
        if (accountId != null) {
            Optional<UserBackup> optionalBackup = userBackupRepository.findById(accountId);

            if (optionalBackup.isPresent()) {
                return optionalBackup.get();
            } else {
                return null;
            }
        }
        return null;
    }

    public List<UserBackup> getBackupAll() {
        return userBackupRepository.findAll();
    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).collect(Collectors.toList());
    }

    private void mapCheckInDays(CheckInDaysDTO checkInDaysDTO, UserBackup userBackup) {
        if (checkInDaysDTO != null) {
            userBackup.setCheckInDays(new CheckInDays(checkInDaysDTO));
        }
    }

    private void mapGoals(GoalsDTO goalsDTO, UserBackup userBackup) {
        if (goalsDTO != null) {
            userBackup.setGoals(new Goals(goalsDTO));
        }
    }

    private void mapAchievements(List<AchievementDTO> achievementDTOList, UserBackup userBackup) {
        if (achievementDTOList != null) {
            userBackup.setAchievements(mapList(achievementDTOList, achievementDTO -> new Achievement(achievementDTO, userBackup)));
        }
    }

    private void mapMissions(List<MissionDTO> missionDTOList, UserBackup userBackup) {
        if (missionDTOList != null) {
            userBackup.setMissions(mapList(missionDTOList, missionDTO -> {
                Mission mission = new Mission(missionDTO, userBackup);
                mission.setRequirements(mapList(missionDTO.requirements(), taskDTO -> {
                    Task task = new Task(taskDTO);
                    task.setUserBackup(userBackup);
                    task.setMission(mission);
                    return task;
                }));
                return mission;
            }));
        }
    }

    private void mapTasks(List<TaskDTO> taskDTOList, UserBackup userBackup) {
        if (taskDTOList != null) {
            userBackup.setTasks(mapList(taskDTOList, taskDTO -> {
                Task task = new Task(taskDTO);
                task.setUserBackup(userBackup);
                return task;
            }));
        }
    }

    private void mapSkills(List<SkillDTO> skillDTOList, UserBackup userBackup) {
        if (skillDTOList != null) {
            userBackup.setSkills(mapList(skillDTOList,  skillDTO -> new Skill(skillDTO, userBackup)));
        }
    }

}
