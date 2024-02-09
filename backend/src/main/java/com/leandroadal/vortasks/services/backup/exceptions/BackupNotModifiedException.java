package com.leandroadal.vortasks.services.backup.exceptions;

public class BackupNotModifiedException extends RuntimeException {
    public BackupNotModifiedException(String message, String userId) {
        super(message + ": " + userId);
    }

    public BackupNotModifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
