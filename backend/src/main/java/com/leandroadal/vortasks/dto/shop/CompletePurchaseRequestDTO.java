package com.leandroadal.vortasks.dto.shop;

import java.math.BigDecimal;

public record CompletePurchaseRequestDTO(
    Long gemsTransactionId,
    BigDecimal paymentToken
) {

}
