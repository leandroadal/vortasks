package com.leandroadal.vortasks.entities.shop.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record GemsPackageRequestDTO(
        @NotBlank(message = "O nome do pacote de gemas não pode estar em branco")
        String nameOfPackage,
        @NotBlank
        String icon,
        @Positive
        int gems,
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor do dinheiro deve ser maior que zero")
        BigDecimal money) {

}
