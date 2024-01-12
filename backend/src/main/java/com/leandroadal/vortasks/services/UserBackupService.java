package com.leandroadal.vortasks.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.repositories.UserBackupRepository;

@Service
public class UserBackupService {

    private final UserBackupRepository accountBackupRepository;

    public UserBackupService(UserBackupRepository accountBackupRepository) {
        this.accountBackupRepository = accountBackupRepository;
    }

    public UserBackup createBackup(UserBackup backup) {
        return accountBackupRepository.save(backup);
    }

    public UserBackup getBackupByAccountId(Long accountId) {
        return accountBackupRepository.findById(accountId).get();
    }

    public List<UserBackup> getBackupAll() {
        return accountBackupRepository.findAll();
    }
}
