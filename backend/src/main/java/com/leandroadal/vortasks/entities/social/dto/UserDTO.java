package com.leandroadal.vortasks.entities.social.dto;

import com.leandroadal.vortasks.entities.user.User;

public record UserDTO(String id, int level) {

    public UserDTO(User user) {
        this(user.getId(), user.getProgressData().getLevel());
    }

}
