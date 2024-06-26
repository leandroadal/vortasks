package com.leandroadal.vortasks.services.social.tasks;

import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMissionTasks;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.social.OnlineMissionRepository;
import com.leandroadal.vortasks.repositories.social.OnlineMissionTasksRepository;
import com.leandroadal.vortasks.security.UserSS;
import com.leandroadal.vortasks.services.exception.DatabaseException;
import com.leandroadal.vortasks.services.exception.ForbiddenAccessException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.user.UserService;

@Service
public class OnlineMissionService {

    @Autowired
    private OnlineMissionRepository repository;

    @Autowired
    private OnlineMissionTasksRepository repoMissionTasks;

    @Autowired
    private UserService userService;

    @Autowired
    private LogSocialTasks log;

    public OnlineMission findOnlineMissionById(String id) {
        OnlineMission mission = getOnlineMissionById(id);
        validateUserAuth(mission.getUser().getId());
        return mission;
    }

    private OnlineMission getOnlineMissionById(String id) {
        try {
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.notFoundOnlineMission(id);
            throw e;
        }
    }

    private void validateUserAuth(String userId) {
        UserSS userSS = UserService.authenticated();
        if (!userSS.getId().equals(userId)) {
            throw new ForbiddenAccessException("Requisição invalida para o usuário");
        }
    }

    public List<OnlineMission> findAll() {
        return repository.findAll();
    }

    public Page<OnlineMission> search(String title, Status status, Type type, Integer page, Integer linesPerPage,
            String direction, String orderBy, boolean byUser) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        UserSS userSS = UserService.authenticated();
        if (byUser) {
            if (type == null) {
                return repository.findDistinctByTitleContainingAndStatusAndUserId(title, status, userSS.getId(),
                        pageRequest);
            } else {
                return repository.findDistinctByTitleContainingAndStatusAndTypeAndUserId(title, status, type,
                        userSS.getId(), pageRequest);
            }
        } else {
            if (type == null) {
                return repository.findDistinctByTitleContainingAndStatus(title, status, pageRequest);
            } else {
                return repository.findDistinctByTitleContainingAndStatusAndType(title, status, type, pageRequest);
            }
        }

    }

    public OnlineMission addOnlineMission(OnlineMission data) {
        UserSS userSS = UserService.authenticated();
        User user = userService.findUserById(userSS.getId());
        data.setUser(user);
        user.getOnlineMissions().add(data);
        data.getRequirements().forEach(req -> req.setOnlineMission(data));

        repository.save(data);
        log.addOnlineMission(data.getId());
        return data;
    }

    public OnlineMission edit(OnlineMission data) {
        OnlineMission onlineMission = getOnlineMissionById(data.getId());
        validateUserAuth(onlineMission.getUser().getId());
        applyEdit(onlineMission, data);
        repository.save(onlineMission);
        log.editOnlineMission(onlineMission.getId());
        return onlineMission;
    }

    private void applyEdit(OnlineMission onlineMission, OnlineMission data) {
        onlineMission.setTitle(data.getTitle());
        onlineMission.setDescription(data.getDescription());
        onlineMission.setRepetition(data.getRepetition());
        onlineMission.setReminder(data.getReminder());
        onlineMission.setStartDate(data.getStartDate());
        onlineMission.setEndDate(data.getEndDate());
        onlineMission.setStatus(data.getStatus());
        onlineMission.setXp(data.getXp());
        onlineMission.setCoins(data.getCoins());
        onlineMission.setType(data.getType());
        onlineMission.setSkillIncrease(data.getSkillIncrease());
        onlineMission.setSkillDecrease(data.getSkillDecrease());
        onlineMission.setLikes(data.getLikes());
    }

    public OnlineMission partialEdit(OnlineMission data) {
        OnlineMission onlineMission = getOnlineMissionById(data.getId());
        validateUserAuth(onlineMission.getUser().getId());
        applyPartialEdit(onlineMission, data);
        repository.save(onlineMission);
        log.partialEditOnlineMission(onlineMission.getId());
        return onlineMission;
    }

    private void applyPartialEdit(OnlineMission onlineMission, OnlineMission data) {
        updateFieldIfNotNull(onlineMission::setTitle, data.getTitle());
        updateFieldIfNotNull(onlineMission::setDescription, data.getDescription());
        updateFieldIfNotNull(onlineMission::setStatus, data.getStatus());
        updateFieldIfPositive(onlineMission::setXp, data.getXp());
        updateFieldIfPositive(onlineMission::setCoins, data.getCoins());
        updateFieldIfNotNull(onlineMission::setType, data.getType());
        updateFieldIfNotNull(onlineMission::setRepetition, data.getRepetition());
        updateFieldIfNotNull(onlineMission::setReminder, data.getReminder());
        updateFieldIfPositive(onlineMission::setSkillIncrease, data.getSkillIncrease());
        updateFieldIfPositive(onlineMission::setSkillDecrease, data.getSkillDecrease());
    }

    private <T> void updateFieldIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private <T extends Number> void updateFieldIfPositive(Consumer<T> setter, T value) {
        if (value != null && value.doubleValue() > 0) {
            setter.accept(value);
        }
    }

    public void deleteOnlineMission(String id) {
        try {
            OnlineMission onlineMission = getOnlineMissionById(id);
            validateUserAuth(onlineMission.getUser().getId());
            repository.deleteById(id);
            log.deleteOnlineMission(id);
        } catch (DataIntegrityViolationException e) {
            log.failDeleteOnlineMission(id);
            throw new DatabaseException(
                    "Não foi possível deletar a entidade pois ainda há relação com outra(s) entidade(s)");
        }
    }

    // ------------------------ Collections ------------------------

    public OnlineMission addToRequirements(String onlineMissionId, List<OnlineMissionTasks> missionTasks) {
        OnlineMission onlineMission = getOnlineMissionById(onlineMissionId);
        validateUserAuth(onlineMission.getUser().getId());
        missionTasks.forEach(req -> req.setOnlineMission(onlineMission));
        onlineMission.getRequirements().addAll(missionTasks);
        repository.save(onlineMission);
        return onlineMission;
    }

    public OnlineMission editRequirements(String onlineMissionId, List<OnlineMissionTasks> data) {
        OnlineMission onlineMission = getOnlineMissionById(onlineMissionId);
        validateUserAuth(onlineMission.getUser().getId());
        for (OnlineMissionTasks task : data) {
            OnlineMissionTasks existingTask = findMissionTasksById(task.getId());
            applyEditMissionTasks(existingTask, task);
        }

        repository.save(onlineMission);
        return onlineMission;
    }

    private void applyEditMissionTasks(OnlineMissionTasks existingTask, OnlineMissionTasks data) {
        existingTask.setTitle(data.getTitle());
        existingTask.setDescription(data.getDescription());
        existingTask.setRepetition(data.getRepetition());
        existingTask.setReminder(data.getReminder());
        existingTask.setStartDate(data.getStartDate());
        existingTask.setEndDate(data.getEndDate());
        existingTask.setStatus(data.getStatus());
        existingTask.setXp(data.getXp());
        existingTask.setCoins(data.getCoins());
        existingTask.setType(data.getType());
        existingTask.setSkillIncrease(data.getSkillIncrease());
        existingTask.setSkillDecrease(data.getSkillDecrease());
    }

    public void removeFromRequirements(String onlineMissionId, String missionTaskId) {
        OnlineMission onlineMission = getOnlineMissionById(onlineMissionId);
        validateUserAuth(onlineMission.getUser().getId());
        OnlineMissionTasks missionTask = findMissionTasksById(missionTaskId);
        onlineMission.getRequirements().remove(missionTask);
        repository.save(onlineMission);
    }

    /*
     * public void removeFromRequirements(String onlineMissionId, Set<String>
     * missionTaskId) {
     * OnlineMission onlineMission = findOnlineMissionById(onlineMissionId);
     * List<OnlineMissionTasks> missionTask = missionTaskId.stream()
     * .map(id -> findMissionTasksById(id))
     * .collect(Collectors.toList());
     * onlineMission.getRequirements().removeAll(missionTask);
     * //onlineMission.getRequirements().remove(data);
     * repository.save(onlineMission);
     * }
     */

    public void clearRequirements(String onlineMissionId) {
        OnlineMission onlineMission = getOnlineMissionById(onlineMissionId);
        validateUserAuth(onlineMission.getUser().getId());
        onlineMission.getRequirements().clear();
        repository.save(onlineMission);
    }

    public OnlineMissionTasks findMissionTasksById(String id) {
        try {
            return repoMissionTasks.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.notFoundOnlineMission(id);
            throw e;
        }
    }

}
