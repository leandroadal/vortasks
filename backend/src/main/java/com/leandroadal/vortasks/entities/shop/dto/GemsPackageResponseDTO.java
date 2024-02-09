package com.leandroadal.vortasks.entities.shop.dto;

import java.math.BigDecimal;

import com.leandroadal.vortasks.entities.shop.GemsPackage;

public record GemsPackageResponseDTO(
        Long id,
        String nameOfPackage,
        String icon,
        int gems,
        BigDecimal money) {
            
    public GemsPackageResponseDTO(GemsPackage data) {
        this(
                data.getId(),
                data.getNameOfPackage(),
                data.getIcon(),
                data.getGems(),
                data.getMoney());
    }
}
