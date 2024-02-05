package com.leandroadal.vortasks.entities.shop.transaction;

import java.math.BigDecimal;

import com.leandroadal.vortasks.entities.shop.GemsPackage;
import com.leandroadal.vortasks.entities.shop.dto.GemsTransactionDTO;
import com.leandroadal.vortasks.entities.shop.enumerators.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GemsTransaction extends AbstractTransaction {

    public GemsTransaction(GemsTransactionDTO data) {
        this.price = data.price();
        this.status = data.status();
        this.setPurchaseDate(data.purchaseDate());
        this.setErrorMessage(data.errorMessage());
    }
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "currency_sell_id")
    private GemsPackage gemsPackage;

}
