package com.leandroadal.vortasks.controllers.shop;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.shop.dto.GemsTransactionDTO;
import com.leandroadal.vortasks.entities.shop.dto.ProductTransactionDTO;
import com.leandroadal.vortasks.entities.shop.dto.TransactionResponseDTO;
import com.leandroadal.vortasks.entities.shop.transaction.GemsTransaction;
import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;
import com.leandroadal.vortasks.repositories.shop.GemsTransactionRepository;
import com.leandroadal.vortasks.repositories.shop.ProductTransactionRepository;

@RestController
@RequestMapping("/shop/my-transactions")
public class TransactionController {

    @Autowired
    private GemsTransactionRepository repository;

    @Autowired
    private ProductTransactionRepository productRepository;

    @GetMapping
    public ResponseEntity<TransactionResponseDTO> myTransactions() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new TransactionResponseDTO(
                                gemsListTransactionDTOs(),
                                productListTransactionDTOs()));
    }

    private List<ProductTransactionDTO> productListTransactionDTOs() {
        List<ProductTransaction> productTransaction = productRepository.findAll();

        return productTransaction.stream().map(ProductTransactionDTO::new)
                .toList();
    }

    private List<GemsTransactionDTO> gemsListTransactionDTOs() {
        List<GemsTransaction> gemsTransaction = repository.findAll();

        return gemsTransaction.stream().map(GemsTransactionDTO::new)
                .collect(Collectors.toList());
    }
}
