package com.leandroadal.vortasks.services.shop.products;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogProductService {

    public void logProductFind(Long id) {
        log.error("Produto com ID: '{}' não encontrado!", id);
    }

    public void logDeleteFailed(Long id) {
        log.error("Produto com ID: '{}' falhou ao deletar!", id);
    }

    protected void logAddProduct(Long id) {
        log.info("Produto com ID: '{}' adicionado com sucesso!", id);
    }

    protected void logEditProduct(Long id) {
        log.info("Produto com ID: '{}' editado com sucesso!", id);
    }

    protected void logPartialEditProduct(Long id) {
        log.info("Atualização parcial do produto com ID: '{}' foi realizada com sucesso!", id);
    }

    protected void logDeleteProduct(Long id) {
        log.info("Produto com ID: '{}' deletado com sucesso!", id);
    }

}
