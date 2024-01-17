package com.leandroadal.vortasks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.AchievementDTO;
import com.leandroadal.vortasks.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.dto.GoalsDTO;
import com.leandroadal.vortasks.dto.LatestBackupResponseDTO;
import com.leandroadal.vortasks.dto.MissionDTO;
import com.leandroadal.vortasks.dto.SkillDTO;
import com.leandroadal.vortasks.dto.TaskDTO;
import com.leandroadal.vortasks.dto.UserBackupDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.UserBackupRepository;

import jakarta.transaction.Transactional;

@Service
public class UserBackupService {

    @Autowired
    private UserBackupRepository userBackupRepository;

    @Transactional
    public UserBackup createBackup(UserBackupDTO userBackupDTO, Account account) {
        UserBackup userBackup = new UserBackup();
        userBackup.setLevel(userBackupDTO.level());
        userBackup.setXp(userBackupDTO.xp());
        userBackup.setLastModified(userBackupDTO.lastModified());

        if (userBackupDTO.checkInDays() != null) {
            CheckInDays checkInDays = new CheckInDays(userBackupDTO.checkInDays());
            checkInDays.setUserBackup(userBackup);
            userBackup.setCheckInDays(checkInDays);
        }

        if (userBackupDTO.goals() != null) {
            Goals goals = new Goals(userBackupDTO.goals());
            goals.setUserBackup(userBackup);
            userBackup.setGoals(goals);
        }

        if (userBackupDTO.achievements() != null) {
            List<Achievement> achievementsList = new ArrayList<>();
            for (AchievementDTO achievementsDTO : userBackupDTO.achievements()) {
                Achievement achievements = new Achievement(achievementsDTO);
                achievements.setUserBackup(userBackup);
                achievementsList.add(achievements);
            }
            userBackup.setAchievements(achievementsList);
        }

        if (userBackupDTO.missions() != null) {
            List<Mission> missionList = new ArrayList<>();
            for (MissionDTO missionDTO : userBackupDTO.missions()) {
                Mission mission = new Mission(missionDTO);
                mission.setUserBackup(userBackup);

                if (missionDTO.requirements() != null) {
                    List<Task> taskReqList = new ArrayList<>();
                    for (TaskDTO taskDTO : missionDTO.requirements()) {
                        Task task = new Task(taskDTO);
                        task.setUserBackup(userBackup);
                        task.setMission(mission);
                        taskReqList.add(task);
                    }
                    mission.setRequirements(taskReqList);
                }
                missionList.add(mission);
            }
            userBackup.setMissions(missionList);
        }

        if (userBackupDTO.tasks() != null) {
            List<Task> taskReqList = new ArrayList<>();
            for (TaskDTO taskDTO : userBackupDTO.tasks()) {
                Task task = new Task(taskDTO);
                task.setUserBackup(userBackup);
                taskReqList.add(task);
            }
            userBackup.setTasks(taskReqList);
        }

        if (userBackupDTO.skills() != null) {
            List<Skill> skillList = new ArrayList<>();
            for (SkillDTO skillDTO : userBackupDTO.skills()) {
                Skill skill = new Skill(skillDTO);
                skill.setUserBackup(userBackup);
                skillList.add(skill);
            }
            userBackup.setSkills(skillList);
        }

        User user = account.getUser();
        user.setBackup(userBackup);
        userBackup.setUser(user);

        return userBackupRepository.save(userBackup);
    }

    public LatestBackupResponseDTO latestBackup(UserBackup backup) {
        int level = backup.getLevel();
        float xp = backup.getXp();

        CheckInDaysDTO checkInDaysDTO = new CheckInDaysDTO(backup.getCheckInDays().getDays(),
                backup.getCheckInDays().getMonth());

        GoalsDTO goalsDTO = new GoalsDTO(backup.getGoals().getDaily(), backup.getGoals().getMonthly());

        List<AchievementDTO> achievementsList = new ArrayList<>();
        if (backup.getAchievements() != null) {
            for (Achievement achievementsDTO : backup.getAchievements()) {
                AchievementDTO achievements = new AchievementDTO(achievementsDTO.getTitle(),
                        achievementsDTO.getDescription(), achievementsDTO.getXp());
                achievementsList.add(achievements);
            }
        }

        List<TaskDTO> taskReqList = new ArrayList<>();
        if (backup.getTasks() != null) {

            for (Task taskDTO : backup.getTasks()) {
                TaskDTO task = new TaskDTO(
                        taskDTO.getStatus(),
                        taskDTO.getName(),
                        taskDTO.getDescription(),
                        taskDTO.getXp(),
                        taskDTO.getCoins(),
                        taskDTO.getType(),
                        taskDTO.getRepetition(),
                        taskDTO.getReminder(),
                        taskDTO.getSkillIncrease(),
                        taskDTO.getSkillDecrease());
                taskReqList.add(task);
            }
        }

        List<MissionDTO> missionList = new ArrayList<>();
        if (backup.getMissions() != null) {

            for (Mission missionDTO : backup.getMissions()) {
                MissionDTO mission = new MissionDTO(
                        missionDTO.getStatus(),
                        missionDTO.getTitle(),
                        missionDTO.getDescription(),
                        missionDTO.getXp(),
                        missionDTO.getCoins(),
                        missionDTO.getType(),
                        missionDTO.getRepetition(),
                        missionDTO.getReminder(),
                        missionDTO.getSkillIncrease(),
                        missionDTO.getSkillDecrease(),
                        getMissionsRequirements(missionDTO));

                missionList.add(mission);
            }
        }

        List<SkillDTO> skillList = new ArrayList<>();
        if (backup.getSkills() != null) {

            for (Skill skillDTO : backup.getSkills()) {
                SkillDTO skill = new SkillDTO(
                        skillDTO.getName(),
                        skillDTO.getXp());
                skillList.add(skill);
            }
        }

        LatestBackupResponseDTO latestBackupResponseDTO = new LatestBackupResponseDTO(level, xp, checkInDaysDTO, goalsDTO,
                backup.getLastModified(), achievementsList, taskReqList, missionList,
                skillList);

        return latestBackupResponseDTO;
    }

    private List<TaskDTO> getMissionsRequirements(Mission missionDTO) {
        if (missionDTO.getRequirements() != null) {
            List<TaskDTO> taskReqList1 = new ArrayList<>();
            for (Task taskDTO : missionDTO.getRequirements()) {
                TaskDTO task = new TaskDTO(
                        taskDTO.getStatus(),
                        taskDTO.getName(),
                        taskDTO.getDescription(),
                        taskDTO.getXp(),
                        taskDTO.getCoins(),
                        taskDTO.getType(),
                        taskDTO.getRepetition(),
                        taskDTO.getReminder(),
                        taskDTO.getSkillIncrease(),
                        taskDTO.getSkillDecrease());

                taskReqList1.add(task);
            }
            return taskReqList1;

        }
        return null;
    }

    public UserBackup getBackupByAccountId(Long accountId) {
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
}
