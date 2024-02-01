package com.leandroadal.vortasks.services.shop;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.dto.shop.ProductRequestDTO;
import com.leandroadal.vortasks.dto.shop.ProductResponseDTO;
import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.repositories.shop.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public Product productById(@NonNull Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public void addProduct(ProductRequestDTO productDTO) {
        Product product = new Product(productDTO);
        productRepository.save(product);
    }

    public void editProduct(@NonNull Long id, ProductRequestDTO productDTO) {
        Product product = productById(id);
        product.edit(productDTO);
        productRepository.save(product);
    }

    public void partialEditProduct(@NonNull Long id, ProductRequestDTO productDTO) {
        Product product = productById(id);

        updateFieldIfNotNull(productDTO.name(), product::setName);
        updateFieldIfNotNull(productDTO.description(), product::setDescription);
        updateFieldIfNotNull(productDTO.type(), product::setType);
        updateFieldIfNotNull(productDTO.icon(), product::setIcon);
        updateFieldIfPositive(productDTO.coins(), product::setCoins);
        updateFieldIfPositive(productDTO.gems(), product::setGems);

        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productById(id);
        productRepository.delete(product);
    }

    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void updateFieldIfPositive(int value, IntConsumer setter) {
        if (value > 0) {
            setter.accept(value);
        }
    }
}
