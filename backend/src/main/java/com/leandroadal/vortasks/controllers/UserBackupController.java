package com.leandroadal.vortasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.UserBackupDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.AccountRepository;
import com.leandroadal.vortasks.services.UserBackupService;

@RestController
@RequestMapping(value = "/account/backup")
public class UserBackupController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserBackupService accountBackupService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccountBackup(@RequestBody UserBackupDTO backup) {
        Account account = accountRepository.findByUsername(backup.username());
        if (account != null) {
            UserBackup createdBackup = accountBackupService.createBackup(backup, account);
            
            if (createdBackup != null) {
                return ResponseEntity.ok("Backup criado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar o backup");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
    }

    @GetMapping("/getBackup/{accountId}")
    public ResponseEntity<UserBackup> getBackup(@PathVariable Long accountId) {
        UserBackup backup = accountBackupService.getBackupByAccountId(accountId);
        if (backup != null) {
            return ResponseEntity.ok(backup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getBackup")
    public List<UserBackup> getAllBackups() {
        return accountBackupService.getBackupAll();
    }
}
