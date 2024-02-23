package com.leandroadal.vortasks.controllers.shop.products;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandroadal.vortasks.entities.shop.dto.ProductRequestDTO;
import com.leandroadal.vortasks.entities.shop.dto.ProductResponseDTO;
import com.leandroadal.vortasks.entities.shop.product.Product;
import com.leandroadal.vortasks.services.shop.products.ProductService;

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
@RequestMapping(value = "/shop/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productDTO) {
        Product data = productDTO.toProduct();
        Product newProduct = service.addProduct(data);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/shop/product/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new ProductResponseDTO(newProduct));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok(products.stream()
                .map(ProductResponseDTO::new)
                .toList());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponseDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "category", defaultValue = "") String category,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        List<Integer> categoriesId = new ArrayList<>();

        if (!category.isEmpty()) {
            categoriesId = Arrays.stream(category.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        Page<Product> list = service.search(name, categoriesId, page, linesPerPage, orderBy, direction);
        Page<ProductResponseDTO> listDto = list.map(obj -> new ProductResponseDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable @NotNull @Positive Long id) {
        Product product = service.productById(id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> editProduct(@PathVariable @Positive Long id,
            @Valid @RequestBody ProductRequestDTO productDTO) {
        Product data = productDTO.toProduct();
        data.setId(id);
        Product product = service.editProduct(data);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateProduct(@PathVariable @Positive Long id,
            @RequestBody ProductRequestDTO productDTO) {
        Product data = productDTO.toProduct();
        data.setId(id);
        service.partialEditProduct(data);
        return ResponseEntity.ok("Atualização parcial do produto realizada com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @Positive Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/desative")
    public ResponseEntity<String> desativeProduct(@PathVariable @Positive Long id) {
        service.desativeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/category")
    public ResponseEntity<String> updateProductCategory(@PathVariable @Positive Long id,
            @RequestBody List<Integer> ids) {
        service.addCategoryToProduct(id, ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/category")
    public ResponseEntity<String> deleteProductCategory(@PathVariable @Positive Long id,
            @RequestBody List<Integer> ids) {
        service.removeProductCategory(id, ids);
        return ResponseEntity.noContent().build();
    }

}
