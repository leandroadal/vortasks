package com.leandroadal.vortasks.repositories.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.shop.transaction.GemsTransaction;

public interface GemsTransactionRepository extends JpaRepository<GemsTransaction, Long> {

    List<GemsTransaction> findAllByUserId(Long userId);

    GemsTransaction findByUserId(Long userId);

}
