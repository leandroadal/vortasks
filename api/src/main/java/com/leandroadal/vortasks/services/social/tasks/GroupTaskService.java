package com.leandroadal.vortasks.services.social.tasks;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
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
import com.leandroadal.vortasks.security.UserSS;
import com.leandroadal.vortasks.services.exception.ForbiddenAccessException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.exception.ValidateException;
import com.leandroadal.vortasks.services.user.UserService;




@Service
public class GroupTaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupTaskRepository repository;

    @Autowired
    private LogSocialTasks log;

    public GroupTask finGroupTask(String id) {
        GroupTask group = getGroupTaskById(id);
        validateUserAuth(group);
        return group;
    }

    private GroupTask getGroupTaskById(String id) {
        try {
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (Exception e) {
            log.notFoundGroupTaskById(id);
            throw e;
        }
    }

    private void validateUserAuth(GroupTask group) {
        UserSS userSS = UserService.authenticated();
        boolean userFound = group.getUsers().stream()
                                             .anyMatch(user -> user.getId().equals(userSS.getId()));
        if (!userFound) {
            throw new ForbiddenAccessException("Usuário incompatível com o usuário requerido no grupo de tarefas: "+ group.getId());
        }
    }

    public List<GroupTask> getGroupTaskList() {
        return repository.findAll();
    }

    public Page<GroupTask> search(String title, Status status, Type type, Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        UserSS userSS = UserService.authenticated();
        if (type == null) {
            return repository.findDistinctByTitleContainingAndStatusAndUsersId(title, status, userSS.getId(), pageRequest);
        } else {
            return repository.findDistinctByTitleContainingAndStatusAndTypeAndUsersId(title, status, userSS.getId(), type, pageRequest);
        }
    }

    @Transactional
    public GroupTask addGroupTask(GroupTask groupTask, Set<String> usernames) {
        UserSS userSS = UserService.authenticated();
        if (!usernames.contains(userSS.getUsername())) usernames.add(userSS.getUsername()); 
            
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
    public GroupTask editGroupTask(GroupTask data) {
        authEdit(data);
        GroupTask existingGroupTask = getGroupTaskById(data.getId());
        applyEdit(existingGroupTask, data);
        repository.save(existingGroupTask);
        log.editGroupTask(existingGroupTask.getId());
        return existingGroupTask;
    }

    private void authEdit(GroupTask groupTask) {
        UserSS userSS = UserService.authenticated();
        if (userSS.getUsername()!= groupTask.getAuthor() || userSS.getUsername()!= groupTask.getEditor()) {
            throw new ForbiddenAccessException("O usuário não possui permissão para realizar edição!!");
        }
    }

    private void applyEdit(GroupTask existingGroupTask, GroupTask data) {
        existingGroupTask.setAuthor(data.getAuthor());
        existingGroupTask.setEditor(data.getEditor());

        existingGroupTask.setTitle(data.getTitle());
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

    

    @Transactional
    public GroupTask partialEditGroupTask(GroupTask data) {
        authEdit(data);
        GroupTask existingGroupTask = getGroupTaskById(data.getId());
        applyPartialEdit(existingGroupTask, data);

        repository.save(existingGroupTask);
        log.partialEditGroupTask(existingGroupTask.getId());
        return existingGroupTask;
    }

    private void applyPartialEdit(GroupTask existingGroupTask, GroupTask data) {
        updateFieldIfNotNull(existingGroupTask::setAuthor, data.getAuthor());
        updateFieldIfNotNull(existingGroupTask::setEditor, data.getEditor());
        updateFieldIfNotNull(existingGroupTask::setTitle, data.getTitle());
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
    
    @Transactional
    public void deleteGroupTask(String id) {
        GroupTask groupTask = getGroupTaskById(id);
        authEdit(groupTask);
        groupTask.getUsers().forEach(user -> user.getGroupTasks()
                                                    .remove(groupTask));
        groupTask.getUsers().clear();
        repository.delete(groupTask);
    }

    public void addUser(String groupId, String username) {
        GroupTask groupTask = getGroupTaskById(groupId);
        validateUserAuth(groupTask);
        User user = userService.findUserByUsername(username);
        
        validateAddNumberUsers(groupTask);
        groupTask.getUsers().add(user);
        
        userService.save(user);
    }

    private void validateAddNumberUsers(GroupTask groupTask) {
        if (groupTask.getUsers().size() >= 5) {
            log.invalidNumberUsers(groupTask.getId());
            throw new ValidateException("a lista de usuários de deve ter tamanho entre 1 e 5");
        }
    }

    public void deleteUser(String groupId, String username) {
        GroupTask groupTask = getGroupTaskById(groupId);
        validateUserAuth(groupTask);
        User user = userService.findUserByUsername(username);
        
        validateRemoveNumberUsers(groupTask);
        groupTask.getUsers().remove(user);
        userService.save(user);
    }

    private void validateRemoveNumberUsers(GroupTask groupTask) {
        if (groupTask.getUsers().size() <= 1) {
            log.invalidNumberUsers(groupTask.getId());
            throw new ValidateException("a lista de usuários de deve ter tamanho entre 1 e 5");
        }
    }

    public List<GroupTask> getGroupTaskList(Optional<User> user) {
        return user.map(u -> u.getGroupTasks()).orElse(Collections.emptyList());
    }
    /* 
    private void validateRemoveNumberUsers(GroupTask groupTask, Set<String> usernames) {
        if ((groupTask.getUsers().size() + usernames.size()) <= 1) {
            log.invalidNumberUsers(groupTask.getId());
            throw new ValidateException("a lista de usuários de deve ter tamanho entre 1 e 5");
        }
    }*/
}
