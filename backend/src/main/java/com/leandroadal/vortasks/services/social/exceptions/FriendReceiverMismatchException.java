package com.leandroadal.vortasks.services.social.exceptions;

public class FriendReceiverMismatchException extends RuntimeException {

    public FriendReceiverMismatchException(String message) {
        super(message);
    }

    public FriendReceiverMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
