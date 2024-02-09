package com.leandroadal.vortasks.entities.shop.dto;

import com.leandroadal.vortasks.entities.shop.enumerators.ProductType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
    @NotBlank(message = "O nome não pode estar em branco")
    String name,

    @NotBlank(message = "A descrição não pode estar em branco")
    String description,

    @NotNull(message = "O tipo de produto não pode ser nulo")
    ProductType type,

    @NotBlank(message = "O ícone não pode estar em branco")
    String icon,

    @Min(value = 0, message = "As moedas devem ser um número maior ou igual a zero")
    int coins,

    @Min(value = 0, message = "As moedas devem ser um número maior ou igual a zero")
    int gems) {

}
