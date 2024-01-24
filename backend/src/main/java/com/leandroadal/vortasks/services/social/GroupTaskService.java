package com.leandroadal.vortasks.services.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.social.GroupTaskDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.social.GroupTask;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.repositories.UserRepository;
import com.leandroadal.vortasks.repositories.GroupTaskRepository;

@Service
public class GroupTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupTaskRepository groupTaskRepository;

    public GroupTask addGroupTask(GroupTaskDTO groupTaskDTO) {
        GroupTask groupTask = new GroupTask(groupTaskDTO.taskDTO(), groupTaskDTO.author(), groupTaskDTO.editor(),
                groupTaskDTO.category());
        for (String username : groupTaskDTO.usernames()) {
            User user = userRepository.findByUsername(username);
            UserProgressData progressData = user.getProgressData();
            groupTask.getProgressData().add(progressData);
            progressData.getGroupTasks().add(groupTask);
        }
        return groupTaskRepository.save(groupTask);
    }

    public GroupTask editGroupTask(Long id, GroupTaskDTO groupTaskDTO) {
        Optional<GroupTask> opGroupTask = groupTaskRepository.findById(id);
        if (opGroupTask.isEmpty()) {
            return null;
        }
    
        GroupTask existingGroupTask = opGroupTask.get();

        existingGroupTask.setAuthor(groupTaskDTO.author());
        existingGroupTask.setCategory(groupTaskDTO.category());
        existingGroupTask.setEditor(groupTaskDTO.editor());

        TaskDTO taskDTO = groupTaskDTO.taskDTO();
        existingGroupTask.setName(taskDTO.name());
        existingGroupTask.setDescription(taskDTO.description());
        existingGroupTask.setXp(taskDTO.xp());
        existingGroupTask.setCoins(taskDTO.coins());
        existingGroupTask.setType(taskDTO.type());
        existingGroupTask.setRepetition(taskDTO.repetition());
        existingGroupTask.setReminder(taskDTO.reminder());
        existingGroupTask.setSkillIncrease(taskDTO.skillIncrease());
        existingGroupTask.setSkillDecrease(taskDTO.skillDecrease());
    
        // Obtém os usuários
        List<UserProgressData> newProgressData = groupTaskDTO.usernames().stream()
                .map(username -> userRepository.findByUsername(username))
                .filter(Objects::nonNull)
                .map(User::getProgressData)
                .collect(Collectors.toList());
    
        // Cria lista com os dados de progresso dos usuários atuais
        List<UserProgressData> progressDataToRemove = new ArrayList<>(existingGroupTask.getProgressData());
        // Remove da lista os dados de progresso dos usuários que devem permanecer apos a edição
        progressDataToRemove.removeAll(newProgressData);
        // Remove a referência à GroupTask dos dados de progresso dos usuários que não estão mais presentes
        progressDataToRemove.forEach(user -> user.getGroupTasks().remove(existingGroupTask));
    
        // Adiciona os novos usuários à lista, se ainda não estiverem presentes
        newProgressData.forEach(newProgress -> {
            if (!existingGroupTask.getProgressData().contains(newProgress)) {
                existingGroupTask.getProgressData().add(newProgress);
                newProgress.getGroupTasks().add(existingGroupTask);
            }
        });   
        return groupTaskRepository.save(existingGroupTask);
    }
    
    public List<GroupTask> getGroupTaskList(Optional<User> user) {
        List<GroupTask> groupTasks = user.get().getProgressData().getGroupTasks();
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
                    groupTask.getProgressData().stream().map(user -> user.getUser().getUsername())
                            .collect(Collectors.toList()));
            groupTaskDTOs.add(groupTaskDTO);
        }
        return groupTaskDTOs;
    }

}
