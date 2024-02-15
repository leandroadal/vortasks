package com.leandroadal.vortasks.repositories.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;

public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, String> {

}
