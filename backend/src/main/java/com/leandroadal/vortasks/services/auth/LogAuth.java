package com.leandroadal.vortasks.services.auth;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogAuth {

    protected void register(String id) {
        log.info("O usuário com ID: '"+id+"' foi registrado com sucesso!");
    }
    
    protected void login(String id) {
        log.info("O usuário com ID: '"+id+"' autenticado com sucesso!");
    }

    protected void logout(String id) {
        log.info("O usuário com ID: '"+id+"' deslogado com sucesso!");
    }

    protected void usernameAlreadyExists(String id) {
        log.info("O usuário com o Username: '"+id+"' já existe!");
    }


}
