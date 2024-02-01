package com.leandroadal.vortasks.services.shop.exceptions;

public class PaymentMismatchException extends RuntimeException {

    public PaymentMismatchException(String message) {
        super(message);
    }

    public PaymentMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
