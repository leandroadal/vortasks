package com.leandroadal.vortasks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.AchievementDTO;
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

        userBackup.setUser(account.getUser());
        if (userBackupDTO.skills() != null) {
            List<Skill> skillList = new ArrayList<>();
            for (SkillDTO skillDTO : userBackupDTO.skills()) {
                Skill skill = new Skill(skillDTO);
                skill.setUserBackup(userBackup);
                skillList.add(skill);
            }
            userBackup.setSkills(skillList);
        }

        return userBackupRepository.save(userBackup);
    }

    public UserBackup getBackupByAccountId(Long accountId) {
        return userBackupRepository.findById(accountId).get();
    }

    public List<UserBackup> getBackupAll() {
        return userBackupRepository.findAll();
    }
}
