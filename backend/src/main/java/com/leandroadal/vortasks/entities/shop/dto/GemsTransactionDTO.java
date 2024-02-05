package com.leandroadal.vortasks.entities.shop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.leandroadal.vortasks.entities.shop.enumerators.PaymentStatus;
import com.leandroadal.vortasks.entities.shop.transaction.GemsTransaction;

public record GemsTransactionDTO(
    Long id, PaymentStatus status , BigDecimal price, LocalDateTime purchaseDate, String errorMessage
) {
    public GemsTransactionDTO(GemsTransaction data) {
        this(
                data.getId(),
                data.getStatus(),
                data.getPrice(),
                data.getPurchaseDate(),
                data.getErrorMessage()
        );
    }
}
