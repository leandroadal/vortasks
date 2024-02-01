package com.leandroadal.vortasks.controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.shop.CompletePurchaseRequestDTO;
import com.leandroadal.vortasks.dto.shop.GemsTransactionDTO;
import com.leandroadal.vortasks.dto.shop.StartPurchaseRequestDTO;
import com.leandroadal.vortasks.services.auth.exceptions.UserNotFoundException;
import com.leandroadal.vortasks.services.shop.PurchaseService;
import com.leandroadal.vortasks.services.shop.exceptions.GemsPackageNotFoundException;
import com.leandroadal.vortasks.services.shop.exceptions.GemsTransactionNotFoundException;
import com.leandroadal.vortasks.services.shop.exceptions.PaymentException;
import com.leandroadal.vortasks.services.shop.exceptions.PaymentMismatchException;
import com.leandroadal.vortasks.services.shop.exceptions.ProductNotFoundException;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/shop/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/gems/start/{userId}")
    public ResponseEntity<GemsTransactionDTO> startGemsPurchase(@PathVariable @NotNull @Positive Long userId, @RequestBody StartPurchaseRequestDTO request) {
        GemsTransactionDTO response;
        try {
            response = purchaseService.startGemsPurchase(userId, request.productOrGemsId());
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | GemsPackageNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
    }

    @PostMapping("/gems/complete/{userId}")
    public ResponseEntity<String> completeGemsPurchase(@PathVariable @NotNull @Positive Long userId, @RequestBody CompletePurchaseRequestDTO request) {
        try {
            purchaseService.completeGemsPurchase(userId, request);
            return ResponseEntity.ok("Compra concluída com sucesso.");
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UserNotFoundException | GemsTransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentMismatchException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/product")
    public ResponseEntity<String> productPurchase(@PathVariable @NotNull @Positive Long userId, @RequestBody StartPurchaseRequestDTO request) {
        try {
            purchaseService.productPurchase(userId, request);
            return ResponseEntity.ok("Compra concluída com sucesso.");
        } catch (UserNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        
    }
}

