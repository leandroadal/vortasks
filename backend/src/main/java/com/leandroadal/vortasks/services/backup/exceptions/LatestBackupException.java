package com.leandroadal.vortasks.services.backup.exceptions;

public class LatestBackupException extends Exception {
    public LatestBackupException(String message) {
        super(message);
    }

    public LatestBackupException(String message, Throwable cause) {
        super(message, cause);
    }
}
