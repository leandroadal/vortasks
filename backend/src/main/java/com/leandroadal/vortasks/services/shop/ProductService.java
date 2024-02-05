package com.leandroadal.vortasks.services.shop;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.shop.dto.ProductRequestDTO;
import com.leandroadal.vortasks.entities.shop.dto.ProductResponseDTO;
import com.leandroadal.vortasks.repositories.shop.ProductRepository;
import com.leandroadal.vortasks.services.shop.exceptions.ProductNotFoundException;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public Product productById(@NonNull Long id) throws ProductNotFoundException {
        try {
            return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Usuário não encontrado"));
        } catch (ProductNotFoundException e) {
            logger.warn("Usuário com ID: '{}' não encontrado!", id);
            throw e;
        }
    }

    public Product addProduct(ProductRequestDTO productDTO) {
        Product product = new Product(productDTO);
        logger.info("Adição do Produto foi realizada com sucesso!");
        return productRepository.save(product);  
    }

    public void editProduct(@NonNull Long id, ProductRequestDTO productDTO) throws ProductNotFoundException {
        Product product = productById(id);
        product.edit(productDTO);
        productRepository.save(product);
        logger.info("Edição do produto com ID: '{}' foi realizada com sucesso!", id);
    }

    public void partialEditProduct(@NonNull Long id, ProductRequestDTO productDTO) throws ProductNotFoundException {
        Product product = productById(id);

        updateFieldIfNotNull(productDTO.name(), product::setName);
        updateFieldIfNotNull(productDTO.description(), product::setDescription);
        updateFieldIfNotNull(productDTO.type(), product::setType);
        updateFieldIfNotNull(productDTO.icon(), product::setIcon);
        updateFieldIfPositive(productDTO.coins(), product::setCoins);
        updateFieldIfPositive(productDTO.gems(), product::setGems);

        productRepository.save(product);
        logger.info("Edição parcial do produto com ID: '{}' foi realizada com sucesso!", id);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
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
