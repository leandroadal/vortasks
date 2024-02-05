package com.leandroadal.vortasks.entities.shop.dto;

import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.shop.enumerators.ProductType;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        ProductType type,
        String icon,
        int coins,
        int gems,
        int totalSales) {

    public ProductResponseDTO(Product data) {
        this(
            data.getId(), 
            data.getName(), 
            data.getDescription(),
            data.getType(),
            data.getIcon(),
            data.getCoins(), 
            data.getGems(), 
            data.getTotalSales());
    }
}
