package com.leandroadal.vortasks.entities.shop.dto;

import java.time.LocalDateTime;

import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;

public record ProductTransactionDTO(
    Long id, int coins, int gems, LocalDateTime purchaseDate, String errorMessage
) {
    public ProductTransactionDTO(ProductTransaction data) {
        this(
                data.getId(),
                data.getPriceInCoins(),
                data.getPriceInGems(),
                data.getPurchaseDate(),
                data.getErrorMessage()
        );
    }
}
