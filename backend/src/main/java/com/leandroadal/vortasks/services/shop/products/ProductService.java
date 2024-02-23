package com.leandroadal.vortasks.services.shop.products;

import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.shop.product.Category;
import com.leandroadal.vortasks.entities.shop.product.Product;
import com.leandroadal.vortasks.repositories.shop.ProductRepository;
import com.leandroadal.vortasks.services.exception.DatabaseException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LogProduct log;

    @Autowired
    private CategoryService categoryService;

    public List<Product> getAllProducts() {
        return productRepository.findAllByActiveTrue();
    }

    public Page<Product> search(String name, List<Integer> categoryIds, Integer page, Integer linesPerPage, String orderBy,
            String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        if (categoryIds == null || categoryIds.isEmpty()) {
            return productRepository.findDistinctByNameContainingAndActiveTrue(name, pageRequest);
        } else {
            List<Category> category = categoryService.getAllByIds(categoryIds);
            return productRepository.findDistinctByNameContainingAndCategoriesInAndActiveTrue(name, category, pageRequest);
        }
    }

    public Product productById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.logProductFind(id);
            throw e;
        }
    }

    public Product getReferenceById(Long id) {
        return productRepository.getReferenceById(id);
    }

    public Product addProduct(Product data) {
        data.setActive(true);
        saveProduct(data);
        log.logAddProduct(data.getId());
        return data;
    }

    public Product editProduct(Product data) {
        Product product = productById(data.getId());
        updateProduct(product, data);
        saveProduct(product);
        log.logEditProduct(data.getId());
        return product;
    }

    public void partialEditProduct(Product data) {
        Product product = productById(data.getId());
        partialEdit(product, data);

        saveProduct(product);
        log.logPartialEditProduct(data.getId());
    }


    public void deleteProduct(Long id) {
        try {
            productById(id);
            productRepository.deleteById(id);
            log.logDeleteProduct(id);
        } catch (DataIntegrityViolationException e) {
            log.logDeleteFailed(id);
            throw new DatabaseException(e.getMessage());
        }
    }

    public void desativeProduct(Long id) {
        Product product = productById(id);
        product.setActive(false);
        saveProduct(product);
    }

    public void addCategoryToProduct(Long productId, List<Integer> ids) {
        Product product = productById(productId);
        List<Category> categories = categoryService.getAllByIds(ids);
        product.getCategories().addAll(categories);

        // Adiciona o Produto em categoria
        for (Category category : categories) {
            category.getProducts().add(product);
            categoryService.saveCategory(category);
        }
        saveProduct(product);  
    }

    public void removeProductCategory(Long productId, List<Integer> ids) {
        Product product = productById(productId);
        List<Category> categories = categoryService.getAllByIds(ids);
        product.getCategories().removeAll(categories);

        for (Category category : categories) {
            category.getProducts().remove(product);
        }
    }

    /* 
    public void updateProductCategory(Product product, Product data) {
        List<Integer> categoryIds = data.getCategories().stream()
                                       .map(Category::getId)
                                       .collect(Collectors.toList());
        List<Category> categories = categoryService.getAllByIds(categoryIds);
        product.getCategories().addAll(categories);
    }*/

    private Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    

    private void updateProduct(Product product, Product data) {
        product.setName(data.getName());
        product.setDescription(data.getDescription());
        product.setIcon(data.getIcon());
        product.setCoins(data.getCoins());
        product.setGems(data.getGems());
        product.setActive(data.getActive());
        //updateCollectionIfNotNull(product.getCategories()::addAll, data.getCategories());
    }
    
    private void partialEdit(Product product, Product data) {
        updateFieldIfNotNull(product::setName, data.getName());
        updateFieldIfNotNull(product::setDescription, data.getDescription());
        updateFieldIfNotNull(product::setIcon, data.getIcon());
        updateFieldIfPositive(product::setCoins, data.getCoins());
        updateFieldIfPositive(product::setGems, data.getGems());
        updateFieldIfNotNull(product::setActive, data.getActive());
        //updateCollectionIfNotNull(product.getCategories()::addAll, data.getCategories());
    }

    private <T> void updateFieldIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private <T extends Number> void updateFieldIfPositive(Consumer<T> setter, T value) {
        if (value != null && value.doubleValue() > 0) {
            setter.accept(value);
        }
    }

    /* 
    private <T, C extends Collection<T>> void updateCollectionIfNotNull(Consumer<C> setter, C newData) {
        if (newData != null) {
            setter.accept(newData);
        }
    }*/
}
