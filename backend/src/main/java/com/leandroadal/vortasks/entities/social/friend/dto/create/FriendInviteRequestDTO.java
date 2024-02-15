package com.leandroadal.vortasks.entities.social.friend.dto.create;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.validation.constraints.NotBlank;

public record FriendInviteRequestDTO(
        @NotBlank(message = "O id é obrigatório") 
        String userId
        ) {

    public FriendInvite toFriendInvite(String friendUserId) {
        User userSender = new User();
        User userReceiver = new User();
        userSender.setId(this.userId);
        userReceiver.setId(friendUserId);
        return new FriendInvite(null, userSender, userReceiver, null, null);
    }
}