package com.leandroadal.vortasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.shop.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
