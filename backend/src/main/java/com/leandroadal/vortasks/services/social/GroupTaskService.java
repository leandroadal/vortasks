package com.leandroadal.vortasks.services.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.social.GroupTask;
import com.leandroadal.vortasks.entities.social.dto.GroupTaskDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.repositories.social.GroupTaskRepository;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.auth.exceptions.UserNotFoundException;
import com.leandroadal.vortasks.services.social.exceptions.GroupTaskNotFoundException;

@Service
public class GroupTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupTaskRepository groupTaskRepository;

    public GroupTask addGroupTask(GroupTaskDTO groupTaskDTO) {
        GroupTask groupTask = new GroupTask(groupTaskDTO);
        for (String username : groupTaskDTO.usernames()) {
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new UserNotFoundException(String.format("O usuário com o username: '{}'", username)));
            UserProgressData progressData = user.getProgressData();
            groupTask.getProgressData().add(progressData);
            progressData.getGroupTasks().add(groupTask);
        }
        return groupTaskRepository.save(groupTask);
    }

    public GroupTask editGroupTask(@NonNull String id, GroupTaskDTO groupTaskDTO) {
        GroupTask existingGroupTask = groupTaskRepository.findById(id)
                .orElseThrow(() -> new GroupTaskNotFoundException(id));
        existingGroupTask.edit(groupTaskDTO);

        // Obtém os usuários
        List<UserProgressData> newProgressData = groupTaskDTO.usernames().stream()
                .map(username -> userRepository.findByUsername(username))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getProgressData)
                .collect(Collectors.toList());

        // Remove os usuários que não estão mais presentes
        existingGroupTask.getProgressData().removeIf(user -> !newProgressData.contains(user));

        // Adiciona os novos usuários à lista, se ainda não estiverem presentes
        newProgressData.stream()
                .filter(newUser -> !existingGroupTask.getProgressData().contains(newUser))
                .forEach(newUser -> {
                    existingGroupTask.getProgressData().add(newUser);
                    newUser.getGroupTasks().add(existingGroupTask);
                });

        return groupTaskRepository.save(existingGroupTask);
    }

    public List<GroupTask> getGroupTaskList(Optional<User> user) {
        return user.map(u -> u.getProgressData().getGroupTasks()).orElse(Collections.emptyList());
    }

    public List<GroupTaskDTO> mapToGroupTaskDTO(List<GroupTask> groupTasks) {
        List<GroupTaskDTO> groupTaskDTOs = new ArrayList<>();
        for (GroupTask groupTask : groupTasks) {
            GroupTaskDTO groupTaskDTO = new GroupTaskDTO(groupTask);
            groupTaskDTOs.add(groupTaskDTO);
        }
        return groupTaskDTOs;
    }

}
