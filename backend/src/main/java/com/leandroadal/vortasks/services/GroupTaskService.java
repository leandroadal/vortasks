package com.leandroadal.vortasks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leandroadal.vortasks.dto.GroupTaskDTO;
import com.leandroadal.vortasks.dto.TaskDTO;
import com.leandroadal.vortasks.entities.social.GroupTask;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.AccountRepository;
import com.leandroadal.vortasks.repositories.GroupTaskRepository;

@Service
public class GroupTaskService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupTaskRepository groupTaskRepository;

    public GroupTask addGroupTask(GroupTaskDTO groupTaskDTO) {
        GroupTask groupTask = new GroupTask(groupTaskDTO.taskDTO(), groupTaskDTO.author(), groupTaskDTO.editor(),
                groupTaskDTO.category());
        for (String users : groupTaskDTO.usernames()) {
            Account account = accountRepository.findByUsername(users);
            User user = account.getUser();
            groupTask.getUsers().add(user);
            user.getGroupTasks().add(groupTask);
        }
        return groupTaskRepository.save(groupTask);
    }

    public GroupTask editGroupTask(Long id, GroupTaskDTO groupTaskDTO) {
        Optional<GroupTask> opGroupTask = groupTaskRepository.findById(id);
        if (opGroupTask.isEmpty()) {
            return null;
        }
    
        GroupTask groupTask = opGroupTask.get();
    
        groupTask.setAuthor(groupTaskDTO.author());
        groupTask.setCategory(groupTaskDTO.category());
        groupTask.setEditor(groupTaskDTO.editor());
        groupTask.setName(groupTaskDTO.taskDTO().name());
        groupTask.setDescription(groupTaskDTO.taskDTO().description());
        groupTask.setXp(groupTaskDTO.taskDTO().xp());
        groupTask.setCoins(groupTaskDTO.taskDTO().coins());
        groupTask.setType(groupTaskDTO.taskDTO().type());
        groupTask.setRepetition(groupTaskDTO.taskDTO().repetition());
        groupTask.setReminder(groupTaskDTO.taskDTO().reminder());
        groupTask.setSkillIncrease(groupTaskDTO.taskDTO().skillIncrease());
        groupTask.setSkillDecrease(groupTaskDTO.taskDTO().skillDecrease());
    
        // Obtém os usuários
        List<User> newUsers = groupTaskDTO.usernames().stream()
                .map(username -> accountRepository.findByUsername(username))
                .filter(Objects::nonNull)
                .map(Account::getUser)
                .collect(Collectors.toList());
    
        // Cria lista com os usuários atuais
        List<User> usersToRemove = new ArrayList<>(groupTask.getUsers());
        // Remove da lista os usuários que devem permanecer apos a edição
        usersToRemove.removeAll(newUsers);
        // Remove a referência à GroupTask dos usuários que não estão mais presentes
        usersToRemove.forEach(user -> user.getGroupTasks().remove(groupTask));
    
        // Adiciona os novos usuários à lista, se ainda não estiverem presentes
        newUsers.forEach(newUser -> {
            if (!groupTask.getUsers().contains(newUser)) {
                groupTask.getUsers().add(newUser);
                newUser.getGroupTasks().add(groupTask);
            }
        });   
        return groupTaskRepository.save(groupTask);
    }
    
    public List<GroupTask> getGroupTaskList(Optional<Account> account) {
        List<GroupTask> groupTasks = account.get().getUser().getGroupTasks();
        return groupTasks;
    }

    public List<GroupTaskDTO> mapToGroupTaskDTO(List<GroupTask> groupTasks) {
        List<GroupTaskDTO> groupTaskDTOs = new ArrayList<>();
        for (GroupTask groupTask : groupTasks) {
            TaskDTO taskDTO = new TaskDTO(
                    groupTask.getStatus(),
                    groupTask.getName(),
                    groupTask.getDescription(),
                    groupTask.getXp(),
                    groupTask.getCoins(),
                    groupTask.getType(),
                    groupTask.getRepetition(),
                    groupTask.getReminder(),
                    groupTask.getSkillIncrease(),
                    groupTask.getSkillDecrease());
            GroupTaskDTO groupTaskDTO = new GroupTaskDTO(
                    taskDTO,
                    groupTask.getAuthor(),
                    groupTask.getEditor(),
                    groupTask.getCategory(),
                    groupTask.getUsers().stream().map(user -> user.getAccount().getUsername())
                            .collect(Collectors.toList()));
            groupTaskDTOs.add(groupTaskDTO);
        }
        return groupTaskDTOs;
    }

}
