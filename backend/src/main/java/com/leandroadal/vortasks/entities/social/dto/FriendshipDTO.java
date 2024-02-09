package com.leandroadal.vortasks.entities.social.dto;

import java.time.Instant;
import java.util.List;

import com.leandroadal.vortasks.entities.social.friend.Friendship;

public record FriendshipDTO(
        String id,
        List<UserDTO> users,
        Instant friendshipDate
) {

    public FriendshipDTO(Friendship friend) {
        this(
            friend.getId(),
            friend.getUsers().stream().map(UserDTO::new).toList(),
            friend.getFriendshipDate());
    }

}
