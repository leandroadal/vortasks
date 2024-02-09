package com.leandroadal.vortasks.services.exception;

public class ObjectNotFoundException extends RuntimeException {

    /* 
    public ObjectNotFoundException(String objectName) {
        super(String.format("O(a) '{}' não foi encontrado(a)!", objectName));
    }*/

    public ObjectNotFoundException(String userId) {
        super(String.format("O Objeto com o ID: {} não foi encontrado!", userId));
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
