package com.leandroadal.vortasks.services.shop.exceptions;

public class GemsTransactionNotFoundException extends Exception {

    public GemsTransactionNotFoundException(String message) {
        super(message);
    }

    public GemsTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
