package com.leandroadal.vortasks.services.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String id) {
        super(String.format("O Objeto com o ID: {} não foi encontrado!", id));
    }

    public ObjectNotFoundException(Integer id) {
        super(String.format("O Objeto com o ID: {} não foi encontrado!", id));
    }

    public ObjectNotFoundException(Long userId) {
        super(String.format("O Objeto com o ID: {} não foi encontrado!", userId));
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
