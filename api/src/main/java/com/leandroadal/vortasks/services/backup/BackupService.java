package com.leandroadal.vortasks.services.backup;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.MissionTasks;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.backup.BackupRepository;
import com.leandroadal.vortasks.security.UserSS;
import com.leandroadal.vortasks.services.backup.exceptions.BackupCreationException;
import com.leandroadal.vortasks.services.backup.exceptions.ObjectNotModifiedException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.user.UserService;

@Service
public class BackupService {

    @Autowired
    private UserService userService;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private BackupAssociationService backupAssociation;

    @Autowired
    private LogBackupService logService;

    public List<Backup> getAllBackups() {
        return backupRepository.findAll();
    }

    public Backup findBackup(String id) {
        try {
            return backupRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            logService.logFindBackup(id);
            throw e;
        }
    }

    public Page<Backup> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return backupRepository.findAll(pageRequest);
    }

    public Backup getBackupByUserId(String userId) {
        try {
            return backupRepository.findByUserId(userId)
                    .orElseThrow(() -> new ObjectNotFoundException(userId));
        } catch (ObjectNotFoundException e) {
            logService.logGetBackupByUserId(userId);
            throw e;
        }
    }

    public User findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    public Backup createBackup(Backup backup) {
        UserSS userSS = UserService.authenticated();
        User user = userService.findUserById(userSS.getId());
        validateBackupCreation(user);

        try {
            // Associa as skills às tasks e missions
            backup.getTasks().forEach(task -> associateSkillsToTask(task, backup.getSkills()));
            backup.getMissions().forEach(mission -> {
                associateSkillsToTask(mission, backup.getSkills());
                mission.getRequirements()
                        .forEach(missionTask -> associateSkillsToTask(missionTask, backup.getSkills()));
            });

            backupAssociation.linkUserAndBackup(user, backup);
            saveBackup(backup);

            logService.logBackupCreationSuccess(user);
            return backup;
        } catch (Exception e) {
            e.printStackTrace();
            logService.logBackupCreationFailure(user);
            throw new BackupCreationException("Falha ao criar o backup", e);
        }
    }

    private void associateSkillsToTask(AbstractTask task, List<Skill> backupSkills) {
        List<Skill> associatedSkills = new ArrayList<>();
        for (Skill skillToAssociate : task.getSkills()) {
            // Encontra a skill correspondente na lista de skills do backup
            Optional<Skill> existingSkill = backupSkills.stream()
                    .filter(s -> s.getName().equals(skillToAssociate.getName()))
                    .findFirst();

            // Adiciona a skill do backup à lista de skills da task
            existingSkill.ifPresent(associatedSkills::add);
        }
        task.setSkills(associatedSkills);
    }

    public Backup getBackup() {
        UserSS userSS = UserService.authenticated();
        return getBackupByUserId(userSS.getId());
    }

    public Backup latestBackup(String lastModified) {
        UserSS userSS = UserService.authenticated();
        Backup backup = getBackupByUserId(userSS.getId());

        if (backup.getLastModified() != null
                && (lastModified.isEmpty() || backup.getLastModified().isAfter(Instant.parse(lastModified)))) {
            logService.logLatestBackupRetrievalSuccess(backup.getId());
            return backup;
        } else {
            logService.logBackupNotModified(backup.getId());
            throw new ObjectNotModifiedException("Backup não modificado para o usuário", backup.getId());
        }
    }

    @Transactional
    public Backup updateBackup(Backup data) {
        UserSS userSS = UserService.authenticated();
        Backup existingBackup = getBackupByUserId(userSS.getId());

        // Atualiza os dados do backup
        updateData(existingBackup, data);

        // Associa as skills após atualizar tasks e missions
        existingBackup.getTasks().forEach(task -> associateSkillsToTask(task, existingBackup.getSkills()));
        existingBackup.getMissions().forEach(mission -> {
            associateSkillsToTask(mission, existingBackup.getSkills());
            mission.getRequirements()
                    .forEach(missionTask -> associateSkillsToTask(missionTask, existingBackup.getSkills()));
        });

        saveBackup(existingBackup);
        logService.logBackupUpdateSuccess(existingBackup.getId());
        return existingBackup;
    }

    @Transactional
    public void deleteUserBackup() {
        UserSS userSS = UserService.authenticated();
        Backup userBackup = getBackupByUserId(userSS.getId());

        backupAssociation.removeReferences(userBackup);
        deleteBackup(userBackup);
        logService.logBackupDeletionSuccess(userSS.getId());
    }

    private void validateBackupCreation(User user) {
        if (user.getBackup() != null) {
            throw new BackupCreationException("Backup já existe para o usuário com ID:", user.getId());
        }
    }

    private void updateData(Backup existingBackup, Backup data) {
        existingBackup.setLastModified(data.getLastModified());

        updateCheckInDays(existingBackup.getCheckInDays(), data.getCheckInDays());
        updateGoals(existingBackup.getGoals(), data.getGoals());
        updateAchievements(existingBackup, data.getAchievements());
        updateTasks(existingBackup, data.getTasks());
        updateMissions(existingBackup, data.getMissions());
        updateSkills(existingBackup, data.getSkills());
    }

    private void updateCheckInDays(CheckInDays existingCheckInDays, CheckInDays newCheckInDays) {
        if (existingCheckInDays != null && newCheckInDays != null) {
            existingCheckInDays.edit(new CheckInDaysDTO(newCheckInDays));
        } else if (newCheckInDays != null) {
            newCheckInDays.setUserBackup(existingCheckInDays.getUserBackup());
            existingCheckInDays = newCheckInDays;
        }
    }

    private void updateGoals(Goals existingGoals, Goals newGoals) {
        if (existingGoals != null && newGoals != null) {
            existingGoals.setDaily(newGoals.getDaily());
            existingGoals.setWeekly(newGoals.getWeekly());
            existingGoals.setMonthly(newGoals.getMonthly());
            existingGoals.setDailyGoalProgress(newGoals.getDailyGoalProgress());
            existingGoals.setWeeklyGoalProgress(newGoals.getWeeklyGoalProgress());
            existingGoals.setMonthlyGoalProgress(newGoals.getMonthlyGoalProgress());
        } else if (newGoals != null) {
            newGoals.setUserBackup(existingGoals.getUserBackup());
            existingGoals = newGoals;
        }
    }

    private void updateAchievements(Backup existingBackup, List<Achievement> newAchievements) {
        List<Achievement> existingAchievements = existingBackup.getAchievements();

        // Identifica os itens a serem removidos
        List<Achievement> achievementsToRemove = existingAchievements.stream()
                .filter(existingAchievement -> !newAchievements.stream()
                        .anyMatch(newAchievement -> newAchievement.getId().equals(existingAchievement.getId())))
                .collect(Collectors.toList());

        // Remove os itens do backup
        existingAchievements.removeAll(achievementsToRemove);

        for (Achievement newAchievement : newAchievements) {
            // verifica se o id enviado já existe na lista de conquistas
            Optional<Achievement> existingAchievement = existingAchievements.stream()
                    .filter(a -> a.getId().equals(newAchievement.getId()))
                    .findFirst();

            if (existingAchievement.isPresent()) {
                // Caso exista faz as modificações necessárias
                existingAchievement.get().edit(new AchievementDTO(newAchievement));
            } else {
                // Caso não exista, cria um novo item
                newAchievement.setUserBackup(existingBackup);
                existingAchievements.add(newAchievement);
            }
        }
    }

    private void updateTasks(Backup existingBackup, List<Task> newTasks) {
        List<Task> existingTasks = existingBackup.getTasks();

        // Remove as tasks existentes que não estão na nova lista
        existingTasks.removeIf(
                existingTask -> !newTasks.stream().anyMatch(newTask -> newTask.getId().equals(existingTask.getId())));

        // Adiciona as novas tasks que não existem na lista existente
        newTasks.forEach(newTask -> {
            if (!existingTasks.stream().anyMatch(t -> t.getId().equals(newTask.getId()))) {
                newTask.setUserBackup(existingBackup);
                existingTasks.add(newTask);
            }
        });

        // Atualiza as tasks existentes com os dados da nova lista
        existingTasks.forEach(existingTask -> newTasks.stream()
                .filter(newTask -> newTask.getId().equals(existingTask.getId()))
                .findFirst()
                .ifPresent(newTask -> updateTaskData(existingTask, newTask)));

        // Associa as skills após atualizar as tasks
        newTasks.forEach(task -> associateSkillsToTask(task, existingBackup.getSkills()));
    }

    private void updateTaskData(Task existingTask, Task newTask) {
        existingTask.setTitle(newTask.getTitle());
        existingTask.setDescription(newTask.getDescription());
        existingTask.setStatus(newTask.getStatus());
        existingTask.setXp(newTask.getXp());
        existingTask.setCoins(newTask.getCoins());
        existingTask.setType(newTask.getType());
        existingTask.setRepetition(newTask.getRepetition());
        existingTask.setReminder(newTask.getReminder());
        existingTask.setSkillIncrease(newTask.getSkillIncrease());
        existingTask.setSkillDecrease(newTask.getSkillDecrease());
        existingTask.setStartDate(newTask.getStartDate());
        existingTask.setEndDate(newTask.getEndDate());
        existingTask.setTheme(newTask.getTheme());
        existingTask.setDifficulty(newTask.getDifficulty());
        existingTask.setFinish(newTask.isFinish());
        existingTask.setDateFinish(newTask.getDateFinish());
    }

    private void updateMissions(Backup existingBackup, List<Mission> newMissions) {
        List<Mission> existingMissions = existingBackup.getMissions();

        existingMissions.removeIf(existingMission -> !newMissions.stream()
                .anyMatch(newMission -> newMission.getId().equals(existingMission.getId())));

        newMissions.forEach(newMission -> {
            if (!existingMissions.stream().anyMatch(m -> m.getId().equals(newMission.getId()))) {
                newMission.setUserBackup(existingBackup);
                existingMissions.add(newMission);
            }
        });

        // Atualiza as missions existentes com os dados da nova lista
        existingMissions.forEach(existingMission -> newMissions.stream()
                .filter(newMission -> newMission.getId().equals(existingMission.getId()))
                .findFirst()
                .ifPresent(newMission -> {
                    updateMissionData(existingMission, newMission);
                    updateMissionTasks(existingMission.getRequirements(), newMission.getRequirements());
                }));

        // Associa as skills após atualizar as missions
        newMissions.forEach(mission -> {
            associateSkillsToTask(mission, existingBackup.getSkills());
            mission.getRequirements()
                    .forEach(missionTask -> associateSkillsToTask(missionTask, existingBackup.getSkills()));
        });
    }

    private void updateMissionData(Mission existingMission, Mission newMission) {
        existingMission.setTitle(newMission.getTitle());
        existingMission.setDescription(newMission.getDescription());
        existingMission.setStatus(newMission.getStatus());
        existingMission.setXp(newMission.getXp());
        existingMission.setCoins(newMission.getCoins());
        existingMission.setType(newMission.getType());
        existingMission.setRepetition(newMission.getRepetition());
        existingMission.setReminder(newMission.getReminder());
        existingMission.setSkillIncrease(newMission.getSkillIncrease());
        existingMission.setSkillDecrease(newMission.getSkillDecrease());
        existingMission.setStartDate(newMission.getStartDate());
        existingMission.setEndDate(newMission.getEndDate());
        existingMission.setTheme(newMission.getTheme());
        existingMission.setDifficulty(newMission.getDifficulty());
        existingMission.setFinish(newMission.isFinish());
        existingMission.setDateFinish(newMission.getDateFinish());
    }

    private void updateMissionTasks(List<MissionTasks> existingMissionTasks, List<MissionTasks> newMissionTasks) {
        List<MissionTasks> missionTasksToRemove = existingMissionTasks.stream()
                .filter(existingMissionTask -> !newMissionTasks.stream()
                        .anyMatch(newMissionTask -> newMissionTask.getId().equals(existingMissionTask.getId())))
                .collect(Collectors.toList());

        existingMissionTasks.removeAll(missionTasksToRemove);

        for (MissionTasks newMissionTask : newMissionTasks) {
            Optional<MissionTasks> existingMissionTask = existingMissionTasks.stream()
                    .filter(mt -> mt.getId().equals(newMissionTask.getId()))
                    .findFirst();

            if (existingMissionTask.isPresent()) {
                updateMissionTaskData(existingMissionTask.get(), newMissionTask);
            } else {
                if (!existingMissionTasks.isEmpty()) {
                    newMissionTask.setMission(existingMissionTasks.get(0).getMission());
                } else {
                    ;
                }
                existingMissionTasks.add(newMissionTask);
            }
        }
    }

    private void updateMissionTaskData(MissionTasks existingMissionTask, MissionTasks newMissionTask) {
        existingMissionTask.setTitle(newMissionTask.getTitle());
        existingMissionTask.setDescription(newMissionTask.getDescription());
        existingMissionTask.setStatus(newMissionTask.getStatus());
        existingMissionTask.setXp(newMissionTask.getXp());
        existingMissionTask.setCoins(newMissionTask.getCoins());
        existingMissionTask.setType(newMissionTask.getType());
        existingMissionTask.setRepetition(newMissionTask.getRepetition());
        existingMissionTask.setReminder(newMissionTask.getReminder());
        existingMissionTask.setSkillIncrease(newMissionTask.getSkillIncrease());
        existingMissionTask.setSkillDecrease(newMissionTask.getSkillDecrease());
        existingMissionTask.setStartDate(newMissionTask.getStartDate());
        existingMissionTask.setEndDate(newMissionTask.getEndDate());
        existingMissionTask.setTheme(newMissionTask.getTheme());
        existingMissionTask.setDifficulty(newMissionTask.getDifficulty());
        existingMissionTask.setFinish(newMissionTask.isFinish());
        existingMissionTask.setDateFinish(newMissionTask.getDateFinish());
    }

    private void updateSkills(Backup existingBackup, List<Skill> newSkills) {
        existingBackup.getSkills().removeIf(existingSkill -> !newSkills.stream()
                .anyMatch(newSkill -> newSkill.getName().equals(existingSkill.getName())));

        newSkills.forEach(newSkill -> {
            if (!existingBackup.getSkills().stream().anyMatch(s -> s.getName().equals(newSkill.getName()))) {
                newSkill.setUserBackup(existingBackup);
                existingBackup.getSkills().add(newSkill);
            }
        });

        // Atualiza as skills existentes com os dados da nova lista
        existingBackup.getSkills().forEach(existingSkill -> newSkills.stream()
                .filter(newSkill -> newSkill.getName().equals(existingSkill.getName()))
                .findFirst()
                .ifPresent(newSkill -> {
                    existingSkill.setXp(newSkill.getXp());
                    existingSkill.setLevel(newSkill.getLevel());
                    existingSkill.setThemes(newSkill.getThemes());
                }));
    }

    private void saveBackup(Backup backup) {
        backupRepository.save(backup);
    }

    private void deleteBackup(Backup backup) {
        backupRepository.delete(backup);
    }
}
