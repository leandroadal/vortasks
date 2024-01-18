package com.leandroadal.vortasks.dto;

import jakarta.validation.constraints.NotBlank;

public record FriendDTO(
        @NotBlank(message = "O nome é obrigatório") String username,
        int level) {

}
