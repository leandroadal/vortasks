package com.leandroadal.vortasks.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.FriendDTO;
import com.leandroadal.vortasks.dto.MissionDTO;
import com.leandroadal.vortasks.dto.OnlineMissionDTO;
import com.leandroadal.vortasks.dto.TaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.social.OnlineMission;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.FriendRepository;
import com.leandroadal.vortasks.repositories.OnlineMissionRepository;

@Service
public class SocialService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private OnlineMissionRepository onlineMissionRepository;

    public Friend addFriend(FriendDTO friendDTO, Account account) {
        Friend friend = new Friend(friendDTO);
        friend.setUser(account.getUser());
        return friendRepository.save(friend);
    }

    public List<FriendDTO> getFriendListDTOs(Optional<Account> account) {
        List<Friend> friends = friendRepository.findByUserId(account.get().getUser().getId());

        List<FriendDTO> friendList = new ArrayList<>();
        if (friends != null) {
            for (Friend friend : friends) {
                FriendDTO friendDTO = new FriendDTO(friend.getUsername(), friend.getLevel());
                friendList.add(friendDTO);
            }
        }
        return friendList;
    }

    public OnlineMission addOnlineMission(OnlineMissionDTO onlineMissionDTO, Account account) {
        OnlineMission onlineMission = new OnlineMission(onlineMissionDTO.missionDTO(), onlineMissionDTO.likes());
        onlineMission.setUser(account.getUser());
        return onlineMissionRepository.save(onlineMission);
    }

    public List<OnlineMissionDTO> getOnlineMissionListDTOs(Optional<Account> account) {
        List<OnlineMission> onlineMissions = onlineMissionRepository.findByUserId(account.get().getUser().getId());

        List<OnlineMissionDTO> onlineMissionList = new ArrayList<>();
        if (onlineMissions != null) {
            for (OnlineMission onlineMission : onlineMissions) {
                MissionDTO missionDTO = new MissionDTO(
                        onlineMission.getStatus(),
                        onlineMission.getTitle(),
                        onlineMission.getDescription(),
                        onlineMission.getXp(),
                        onlineMission.getCoins(),
                        onlineMission.getType(),
                        onlineMission.getRepetition(),
                        onlineMission.getReminder(),
                        onlineMission.getSkillIncrease(),
                        onlineMission.getSkillDecrease(),
                        getMissionRequirements(onlineMission));
                OnlineMissionDTO onlineMissionDTO = new OnlineMissionDTO(missionDTO, onlineMission.getLikes());
                onlineMissionList.add(onlineMissionDTO);
            }
        }
        return onlineMissionList;
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
