package com.leandroadal.vortasks.services.social.tasks;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.social.GroupTaskRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.user.UserService;

import jakarta.validation.ValidationException;



@Service
public class GroupTaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupTaskRepository repository;

    @Autowired
    private LogSocialTasks log;

    public List<GroupTask> getGroupTaskList() {
        return repository.findAll();
    }

    public GroupTask findGroupTaskById(String id) {
        try {
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (Exception e) {
            log.notFoundGroupTaskById(id);
            throw e;
        }
    }

    public Page<GroupTask> search(String name, Status status, Type type, Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        if (type == null) {
            return repository.findDistinctByNameContainingAndStatus(name, status, pageRequest);
        } else {
            return repository.findDistinctByNameContainingAndStatusAndType(name, status, type, pageRequest);
        }
    }

    @Transactional
    public GroupTask addGroupTask(GroupTask groupTask, Set<String> usernames) {
        for (String username : usernames) {
            User user = userService.findUserByUsername(username);
            groupTask.getUsers().add(user);
            user.getGroupTasks().add(groupTask);
        }
        repository.save(groupTask);
        log.addGroupTask(groupTask.getId());
        return groupTask;
    }

    @Transactional
    public GroupTask editGroupTask(GroupTask data, Set<String> usernames) {
        // TODO verificar se quem esta solicitando a edição é um autor ou editor para permitir a edição
        GroupTask existingGroupTask = findGroupTaskById(data.getId());
        applyEdit(existingGroupTask, data);
        updateUsersList(existingGroupTask, usernames);
        repository.save(existingGroupTask);
        log.editGroupTask(existingGroupTask.getId());
        return existingGroupTask;
    }

    private void applyEdit(GroupTask existingGroupTask, GroupTask data) {
        existingGroupTask.setAuthor(data.getAuthor());
        existingGroupTask.setEditor(data.getEditor());

        existingGroupTask.setName(data.getName());
        existingGroupTask.setDescription(data.getDescription());
        existingGroupTask.setStatus(data.getStatus());
        existingGroupTask.setXp(data.getXp());
        existingGroupTask.setCoins(data.getCoins());
        existingGroupTask.setType(data.getType());
        existingGroupTask.setRepetition(data.getRepetition());
        existingGroupTask.setReminder(data.getReminder());
        existingGroupTask.setSkillIncrease(data.getSkillIncrease());
        existingGroupTask.setSkillDecrease(data.getSkillDecrease());        
    }

    private void updateUsersList(GroupTask existingGroupTask, Set<String> usernames) { // TODO trocar para clear e addAll
        // Obtém os usuários
        Set<User> newUsersList = usernames.stream()
              .map(username -> userService.findUserByUsername(username))
              .collect(Collectors.toSet());

        // Remove os usuários que não estão mais presentes
        existingGroupTask.getUsers().removeIf(user ->!newUsersList.contains(user));

        // Adiciona os novos usuários à lista, se ainda não estiverem presentes
        newUsersList.stream()
              .filter(newUser ->!existingGroupTask.getUsers().contains(newUser))
              .forEach(newUser -> {
                    existingGroupTask.getUsers().add(newUser);
                    newUser.getGroupTasks().add(existingGroupTask);
                });
    }

    @Transactional
    public GroupTask partialEditGroupTask(GroupTask data, Set<String> usernames) {
        // TODO verificar se quem esta solicitando a edição é um autor ou editor para permitir a edição
        GroupTask existingGroupTask = findGroupTaskById(data.getId());
        applyPartialEdit(existingGroupTask, data);

        if (usernames == null) {
            return repository.save(existingGroupTask);
        }

        validateNumberUsers(existingGroupTask.getId(), usernames);
        updateUsersList(existingGroupTask, usernames);
        repository.save(existingGroupTask);
        log.partialEditGroupTask(existingGroupTask.getId());
        return existingGroupTask;
    }

    private void validateNumberUsers(String groupTaskId, Set<String> usernames) {
        if (usernames.size() == 0 || usernames.size() > 5) {
            log.invalidNumberUsers(groupTaskId);
            throw new ValidationException("a lista de usuários de deve ter tamanho entre 1 e 5");
        }
    }

    private void applyPartialEdit(GroupTask existingGroupTask, GroupTask data) {
        updateFieldIfNotNull(existingGroupTask::setAuthor, data.getAuthor());
        updateFieldIfNotNull(existingGroupTask::setEditor, data.getEditor());
        updateFieldIfNotNull(existingGroupTask::setName, data.getName());
        updateFieldIfNotNull(existingGroupTask::setDescription, data.getDescription());
        updateFieldIfNotNull(existingGroupTask::setStatus, data.getStatus());
        updateFieldIfPositive(existingGroupTask::setXp, data.getXp());
        updateFieldIfPositive(existingGroupTask::setCoins, data.getCoins());
        updateFieldIfNotNull(existingGroupTask::setType, data.getType());
        updateFieldIfPositive(existingGroupTask::setRepetition, data.getRepetition());
        updateFieldIfNotNull(existingGroupTask::setReminder, data.getReminder());
        updateFieldIfPositive(existingGroupTask::setSkillIncrease, data.getSkillIncrease());
        updateFieldIfPositive(existingGroupTask::setSkillDecrease, data.getSkillDecrease());
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

    public void complete(String id) {
        GroupTask groupTask = findGroupTaskById(id);
        groupTask.setStatus(Status.COMPLETED);
        repository.save(groupTask);
    }

    public void fail(String id) {
        GroupTask groupTask = findGroupTaskById(id);
        groupTask.setStatus(Status.FAILED);
        repository.save(groupTask);
    }
    
    @Transactional
    public void deleteGroupTask(String id) {
        // TODO verificar se quem esta solicitando a exclusão é um autor ou editor para permitir excluir
        GroupTask groupTask = findGroupTaskById(id);
        groupTask.getUsers().forEach(user -> user.getGroupTasks()
                                                    .remove(groupTask));
        groupTask.getUsers().clear();
        repository.delete(groupTask);
    }

    public List<GroupTask> getGroupTaskList(Optional<User> user) {
        return user.map(u -> u.getGroupTasks()).orElse(Collections.emptyList());
    }
}
