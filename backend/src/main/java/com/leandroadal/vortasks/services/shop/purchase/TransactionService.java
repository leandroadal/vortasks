package com.leandroadal.vortasks.services.shop.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.shop.transaction.GemsTransaction;
import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;
import com.leandroadal.vortasks.repositories.shop.GemsTransactionRepository;
import com.leandroadal.vortasks.repositories.shop.ProductTransactionRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class TransactionService {
    
    @Autowired
    private GemsTransactionRepository gemsRepository;

    @Autowired
    private ProductTransactionRepository productRepository;

    @Autowired
    private LogPurchaseService log;

    public List<ProductTransaction> productListTransaction() {
        return productRepository.findAll();
    }

    public List<GemsTransaction> gemsListTransaction() {
        return gemsRepository.findAll();
    }

    public GemsTransaction findGemsTransaction(String id) {
        try {
            return gemsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.gemsTransactionNotFound(id);
            throw e;
        }
        
    }

    public ProductTransaction findProductTransaction(String id) {
        try {
           return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id)); 
        } catch (ObjectNotFoundException e) {
            log.productTransactionNotFound(id);
            throw e;
        }
    }

    protected void saveProductTransaction(ProductTransaction transaction) {
        productRepository.save(transaction);
    }

    protected void saveGemsTransaction(GemsTransaction transaction) {
        gemsRepository.save(transaction);
    }

    public Page<ProductTransaction> productListTransactionPage(Integer page, Integer linesPerPage, String orderBy,
            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return productRepository.findAll(pageRequest);
    }

    public Page<GemsTransaction> gemsListTransactionPage(Integer page, Integer linesPerPage, String orderBy,
            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return gemsRepository.findAll(pageRequest);
    }

}
