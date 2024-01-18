package com.leandroadal.vortasks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.FriendDTO;
import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.FriendRepository;

@Service
public class SocialService {
    
    @Autowired
    private FriendRepository friendRepository;

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
}
