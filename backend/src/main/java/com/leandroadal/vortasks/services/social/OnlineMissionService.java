package com.leandroadal.vortasks.services.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.social.OnlineMissionDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.social.OnlineMission;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.OnlineMissionRepository;

@Service
public class OnlineMissionService {

    @Autowired
    private OnlineMissionRepository onlineMissionRepository;

    public OnlineMission addOnlineMission(OnlineMissionDTO onlineMissionDTO, User account) {
        List<Task> taskList = createTaskList(onlineMissionDTO.requirements());
        
        OnlineMission onlineMission = new OnlineMission(onlineMissionDTO);
        onlineMission.setProgressData(account.getProgressData());
        onlineMission.setRequirements(taskList);
        taskList.forEach(task -> task.setOnlineMission(onlineMission));
        return onlineMissionRepository.save(onlineMission);
    }

    public List<OnlineMissionDTO> getOnlineMissionListDTOs(Optional<User> account) {
        List<OnlineMission> onlineMissions = onlineMissionRepository.findByProgressDataId(account.get().getProgressData().getId());

        List<OnlineMissionDTO> onlineMissionList = new ArrayList<>();
        if (onlineMissions != null) {
            for (OnlineMission onlineMission : onlineMissions) {
                OnlineMissionDTO onlineMissionDTO = new OnlineMissionDTO( onlineMission, getMissionRequirements(onlineMission));
                onlineMissionList.add(onlineMissionDTO);
            }
        }
        return onlineMissionList;
    }

    private List<Task> createTaskList(List<TaskDTO> taskDTOList) {
        if (taskDTOList != null) {
            List<Task> taskList = new ArrayList<>();
            for (TaskDTO taskDTO : taskDTOList) {
                Task task = new Task(taskDTO);
                taskList.add(task);
            }
            return taskList;
        } else {
            return Collections.emptyList();
        }
    }

    private List<TaskDTO> getMissionRequirements(OnlineMission mission) {
        if (mission.getRequirements() != null) {
            List<TaskDTO> taskRequirements = new ArrayList<>();
            for (Task task : mission.getRequirements()) {
                TaskDTO taskDTO = new TaskDTO(
                        task.getStatus(),
                        task.getName(),
                        task.getDescription(),
                        task.getXp(),
                        task.getCoins(),
                        task.getType(),
                        task.getRepetition(),
                        task.getReminder(),
                        task.getSkillIncrease(),
                        task.getSkillDecrease());
                taskRequirements.add(taskDTO);
            }
            return taskRequirements;
        } else {
            return Collections.emptyList();
        }
    }

}
