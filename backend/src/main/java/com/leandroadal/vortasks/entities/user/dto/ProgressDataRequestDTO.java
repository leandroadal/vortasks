package com.leandroadal.vortasks.entities.user.dto;

import com.leandroadal.vortasks.entities.user.ProgressData;

import jakarta.validation.constraints.Min;

public record ProgressDataRequestDTO(
    @Min(value = 0, message = "A quantidade de moedas deve ser um número maior ou igual a zero")
    int coins,
    
    @Min(value = 0, message = "A quantidade de gemas deve ser um número maior ou igual a zero")
    int gems,
    
    @Min(value = 0, message = "O nível deve ser um número maior ou igual a zero")
    int level,
    
    @Min(value = 0, message = "A experiência deve ser um número maior ou igual a zero")
    float xp
) {
    public ProgressData toProgressData() {
        return new ProgressData(coins, gems, level, xp, null);
    }
    
}
