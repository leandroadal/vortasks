package com.leandroadal.vortasks.dto.shop;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        int coins,
        int gems) {

}
