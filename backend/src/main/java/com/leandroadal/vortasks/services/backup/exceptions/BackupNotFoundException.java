package com.leandroadal.vortasks.services.backup.exceptions;

public class BackupNotFoundException extends Exception {
    public BackupNotFoundException(String message) {
        super(message);
    }

    public BackupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
