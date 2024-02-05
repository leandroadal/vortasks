package com.leandroadal.vortasks.controllers.shop;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.shop.dto.ProductRequestDTO;
import com.leandroadal.vortasks.entities.shop.dto.ProductResponseDTO;
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
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO entity) {
        Product product = productService.addProduct(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                   .path("/{id}")
                                   .buildAndExpand(product.getId())
                                   .toUri();
        return ResponseEntity.created(uri).body(new ProductResponseDTO(product));
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
