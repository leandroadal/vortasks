package com.leandroadal.vortasks.dto.shop;

import java.util.List;

public record TransactionResponseDTO(
        List<GemsTransactionDTO> gemsTransactions,
        List<ProductTransactionDTO> productsTransactions){

}
