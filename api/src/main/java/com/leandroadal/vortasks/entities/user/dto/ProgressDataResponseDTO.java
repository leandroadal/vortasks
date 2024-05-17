package com.leandroadal.vortasks.entities.user.dto;

import com.leandroadal.vortasks.entities.user.ProgressData;

public record ProgressDataResponseDTO(
    String progressId,
    int coins,
    int gems,
    int level,
    float xp
) {

    public ProgressDataResponseDTO(ProgressData data) {
        this(data.getId(), data.getCoins(), data.getGems(), data.getLevel(), data.getXp());
    }
    
}
