package com.leandroadal.vortasks.services.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.social.FriendDTO;
import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public Friend addFriend(FriendDTO friendDTO, User user) {
        Friend friend = new Friend(friendDTO);
        friend.setProgressData(user.getProgressData());
        return friendRepository.save(friend);
    }

    public List<FriendDTO> getFriendListDTOs(Optional<User> user) {
        List<Friend> friends = friendRepository.findByProgressDataId(user.get().getProgressData().getId());

        List<FriendDTO> friendList = new ArrayList<>();
        if (friends != null) {
            for (Friend friend : friends) {
                FriendDTO friendDTO = new FriendDTO(friend.getUsername(), friend.getLevel());
                friendList.add(friendDTO);
            }
        }
        return friendList;
    }

}
