package com.leandroadal.vortasks.controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.shop.ProductRequestDTO;
import com.leandroadal.vortasks.dto.shop.ProductResponseDTO;
import com.leandroadal.vortasks.services.shop.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequestDTO entity) {
        productService.addProduct(entity);

        return ResponseEntity.status(HttpStatus.OK).body("Adição do produto realizada com sucesso");
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> editProduct(@PathVariable @NotNull @Positive Long id, @RequestBody ProductRequestDTO productDTO) {
        productService.editProduct(id, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Atualização do produto realizada com sucesso");
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<String> partialUpdateProduct(@PathVariable @NotNull @Positive Long id, @RequestBody ProductRequestDTO productDTO) {
        productService.partialEditProduct(id, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Atualização parcial do produto realizada com sucesso");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @NotNull @Positive Long id) {
        productService.deleteProduct(id);;
        return ResponseEntity.status(HttpStatus.OK).body("Remoção do produto realizada com sucesso");
    }
}
