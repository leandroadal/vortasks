package com.leandroadal.vortasks.services.backup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.dto.BackupCreateDTO;
import com.leandroadal.vortasks.entities.backup.dto.BackupResponseDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractMissionCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractTaskCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AchievementCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.CheckInDaysCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.GoalsCreateDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.SkillCreateDTO;
import com.leandroadal.vortasks.repositories.backup.AchievementRepository;
import com.leandroadal.vortasks.repositories.backup.MissionRepository;
import com.leandroadal.vortasks.repositories.backup.SkillRepository;
import com.leandroadal.vortasks.repositories.backup.TaskRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class MappingBackupService {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TaskRepository taskRepository;

    public void mapBackupData(BackupCreateDTO backupDTO, Backup backup) {
        mapCheckInDays(backupDTO.checkInDays(), backup);
        mapGoals(backupDTO.goals(), backup);
        mapAchievements(backupDTO.achievements(), backup);
        mapMissions(backupDTO.missions(), backup);
        mapTasks(backupDTO.tasks(), backup);
        mapSkills(backupDTO.skills(), backup);
    }

    private void mapCheckInDays(CheckInDaysCreateDTO checkInDaysDTO, Backup userBackup) {
        userBackup.setCheckInDays(new CheckInDays(checkInDaysDTO, userBackup));
    }

    private void mapGoals(GoalsCreateDTO goalsDTO, Backup userBackup) {
        userBackup.setGoals(new Goals(goalsDTO, userBackup));
    }

    private void mapAchievements(List<AchievementCreateDTO> achievementDTOList, Backup backup) {
        backup.setAchievements(
                mapList(achievementDTOList,
                        achievementDTO -> new Achievement(achievementDTO, backup)));
    }

    private void mapMissions(List<AbstractMissionCreateDTO> missionDTOList, Backup userBackup) {
        userBackup.setMissions(mapList(missionDTOList, missionDTO -> {
            Mission mission = new Mission(missionDTO, userBackup);
            mission.setRequirements(
                    mapList(missionDTO.requirements(),
                            taskDTO -> new MissionTasks(taskDTO, mission)));
            return mission;
        }));

    }

    private void mapTasks(List<AbstractTaskCreateDTO> taskDTOList, Backup userBackup) {
        userBackup.setTasks(mapList(taskDTOList,
                taskDTO -> new Task(taskDTO, userBackup)));
    }

    private void mapSkills(List<SkillCreateDTO> skillDTOList, Backup userBackup) {
        userBackup.setSkills(mapList(skillDTOList,
                skillDTO -> new Skill(skillDTO, userBackup)));

    }

    private <T, U> List<U> mapList(List<T> sourceList, Function<T, U> mapper) {
        return sourceList.stream().map(mapper).toList();
    }


   
    public Backup mapUpdateBackup(BackupResponseDTO data, Backup backup) {
        updateCheckInDays(data.checkInDays(), backup);
        updateGoals(data.goals(), backup);
        updateAchievements(data.achievements(), backup);
        updateMissions(data.missions(), backup);
        updateTasks(data.tasks(), backup);
        updateSkills(data.skills(), backup);

        return backup;
    }

    private void updateCheckInDays(CheckInDaysDTO data, Backup backup) {
        backup.getCheckInDays().edit(data);
    }

    private void updateGoals(GoalsDTO data, Backup backup) {
        backup.getGoals().edit(data);
    }

    private void updateAchievements(List<AchievementDTO> achievementDTOList, Backup backup) {
        List<Achievement> achievements = new ArrayList<>();

        for (AchievementDTO achievementDTO : achievementDTOList) {
            Achievement achievement = achievementRepository.findById(achievementDTO.id())
                    .orElseThrow(() -> new ObjectNotFoundException("Conquista"));
            achievement.edit(achievementDTO);
            achievements.add(achievement);
        }

        backup.setAchievements(achievements);
    }

    private void updateMissions(List<AbstractMissionDTO> missionDTOList, Backup backup) {
        List<Mission> missions = new ArrayList<>();

        for (AbstractMissionDTO missionDTO : missionDTOList) {
            Mission mission = missionRepository.findById(missionDTO.id())
                    .orElseThrow(() -> new ObjectNotFoundException("Missão"));
            mission.edit(missionDTO);
            missions.add(mission);
        }
        backup.setMissions(missions);
    }

    private void updateTasks(List<AbstractTaskDTO> taskDTOList, Backup backup) {
        List<Task> tasks = new ArrayList<>();

        for (AbstractTaskDTO taskDTO : taskDTOList) {
            Task task = taskRepository.findById(taskDTO.id())
                    .orElseThrow(() -> new ObjectNotFoundException("Tarefa"));
            //task.edit(taskDTO);
            tasks.add(task);
        }
        backup.setTasks(tasks);
    }

    private void updateSkills(List<SkillDTO> skillDTOList, Backup backup) {
        List<Skill> skills = new ArrayList<>();

        for (SkillDTO skillDTO : skillDTOList) {
            Skill skill = skillRepository.findById(skillDTO.id())
                    .orElseThrow(() -> new ObjectNotFoundException("Habilidade"));
            skill.edit(skillDTO);
            skills.add(skill);
        }
        backup.setSkills(skills);
    }
}
