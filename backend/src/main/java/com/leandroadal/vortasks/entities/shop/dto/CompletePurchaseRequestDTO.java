package com.leandroadal.vortasks.entities.shop.dto;

import java.math.BigDecimal;

public record CompletePurchaseRequestDTO(
    Long gemsTransactionId,
    BigDecimal paymentToken
) {

}
