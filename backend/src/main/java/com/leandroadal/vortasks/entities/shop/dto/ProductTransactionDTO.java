package com.leandroadal.vortasks.entities.shop.dto;

import java.time.Instant;
import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;

public record ProductTransactionDTO(
    String id, int coins, int gems, Instant purchaseDate, String errorMessage
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
