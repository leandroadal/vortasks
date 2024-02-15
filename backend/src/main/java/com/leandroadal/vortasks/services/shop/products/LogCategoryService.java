package com.leandroadal.vortasks.services.shop.products;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogCategoryService {

    protected void categoryNotFound(Integer id) {
        log.error("Categoria com ID: '{}' não encontrada!", id);
    }

    protected void logCategoryDeleteFailed(Integer id) {
        log.error("Erro de DataIntegrityViolation ao tenta deletar o ID: {}", id);
    }

    protected void logAddCategory(Integer id) {
        log.info("Categoria com ID: '{}' criada com sucesso!", id);
    }

    protected void logCategoryEdit(Integer id) {
        log.info("Categoria com ID: '{}' foi editada com sucesso!", id);
    }

    protected void logPartialEditCategory(Integer id) {;
        log.info("Atualização parcial da Categoria com ID: '{}' foi realizada com sucesso!", id);
    }

    protected void logCategoryDelete(Integer id) {
        log.info("Categoria com ID: '{}' foi deletada com sucesso!", id);
    }

}
