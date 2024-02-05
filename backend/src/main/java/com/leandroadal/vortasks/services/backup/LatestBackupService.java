package com.leandroadal.vortasks.services.backup;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.services.backup.exceptions.BackupNotModifiedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LatestBackupService {

    @Autowired
    private BackupOperationService backupService;

    public UserBackup latestBackup(Long userId, LocalDateTime lastModified) {
        UserBackup backup = backupService.getBackupByUserId(userId);

        if (lastModified == null || backup.getLastModified().isAfter(lastModified)) {
            logLatestBackupRetrievalSuccess(userId);
            return backup;
        } else {
            logBackupNotModified(userId);
            throw new BackupNotModifiedException("Backup não modificado para o usuário", userId);
        }
    }

    private void logBackupNotModified(Long userId) {
        log.info("Backup não modificado para o usuário {}", userId);
    }

    private void logLatestBackupRetrievalSuccess(Long userId) {
        log.info("Último backup do usuário {} retornado com sucesso", userId);
    }
}
