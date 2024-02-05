package com.leandroadal.vortasks.entities.social.dto;

import com.leandroadal.vortasks.entities.social.Friend;

import jakarta.validation.constraints.NotBlank;

public record FriendDTO(
        @NotBlank(message = "O nome é obrigatório") String username,
        int level) {

    public FriendDTO(Friend friend) {
        this(
            friend.getUsername(),
            friend.getLevel());
    }
}
