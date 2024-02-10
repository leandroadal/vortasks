package com.leandroadal.vortasks.services.shop.products;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogGemsPackageService {

    protected void logGemsPackageNotFind(Long id) {
        log.error("Pacote de gemas com ID: '{}' não encontrado!", id);
    }
    
    protected void logGemsPackageCreation(Long id) {
        log.info("Pacote de gemas com ID: '{}' criado com sucesso!", id);
    }

    protected void logGemsPackageEdit(Long id) {
        log.info("Pacote de gemas com ID: '{}' foi editado com sucesso!", id);
    }

    protected void logGemsPackagePartialEdit(Long id) {
        log.info("Atualização parcial do pacote de gemas com ID: '{}' foi realizada com sucesso!", id);
    }

    protected void logGemsPackageDelete(Long id) {
        log.info("Pacote de gemas com ID: '{}' foi deletado com sucesso!", id);
    }

}
