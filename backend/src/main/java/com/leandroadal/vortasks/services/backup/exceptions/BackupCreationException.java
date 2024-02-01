package com.leandroadal.vortasks.services.backup.exceptions;

public class BackupCreationException extends Exception {
    public BackupCreationException(String message) {
        super(message);
    }

    public BackupCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
