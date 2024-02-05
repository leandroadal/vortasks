package com.leandroadal.vortasks.services.social;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;
import com.leandroadal.vortasks.entities.social.OnlineMission;
import com.leandroadal.vortasks.entities.social.OnlineMissionTasks;
import com.leandroadal.vortasks.entities.social.dto.OnlineMissionDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.social.OnlineMissionRepository;

@Service
public class OnlineMissionService {

    @Autowired
    private OnlineMissionRepository onlineMissionRepository;

    public OnlineMission addOnlineMission(OnlineMissionDTO onlineMissionDTO, User account) {
        List<OnlineMissionTasks> taskList = createTaskList(onlineMissionDTO.requirements());
        
        OnlineMission onlineMission = new OnlineMission(onlineMissionDTO, account.getProgressData(), taskList);
        taskList.forEach(task -> task.setOnlineMission(onlineMission));
        return onlineMissionRepository.save(onlineMission);
    }

    private List<OnlineMissionTasks> createTaskList(List<AbstractTaskDTO> taskDTOList) {
        return taskDTOList == null ? Collections.emptyList() :
                taskDTOList.stream()
                        .map(OnlineMissionTasks::new)
                        .toList();
    }

    public List<OnlineMissionDTO> getOnlineMissionListDTOs(Optional<User> account) {
        return onlineMissionRepository.findByProgressDataId(account.get().getProgressData().getId())
                .stream()
                .map(mission -> new OnlineMissionDTO(mission, getMissionRequirements(mission)))
                .toList();
    }
    
    private List<AbstractTaskDTO> getMissionRequirements(OnlineMission mission) {
        return mission.getRequirements() == null ? Collections.emptyList() :
                mission.getRequirements().stream()
                        .map(AbstractTaskDTO::new)
                        .toList();
    }

}
