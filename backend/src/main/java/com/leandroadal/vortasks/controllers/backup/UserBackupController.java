package com.leandroadal.vortasks.controllers.backup;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.dto.UserBackupDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.services.backup.CreateBackupService;
import com.leandroadal.vortasks.services.backup.DeleteBackupService;
import com.leandroadal.vortasks.services.backup.LatestBackupService;
import com.leandroadal.vortasks.services.backup.UpdateBackupService;
import com.leandroadal.vortasks.services.backup.BackupOperationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/account/backup")
public class UserBackupController {

    @Autowired
    private BackupOperationService service;

    @Autowired
    private CreateBackupService createService;

    @Autowired
    private UpdateBackupService updateService;

    @Autowired
    private DeleteBackupService deleteService;

    @Autowired
    private LatestBackupService latestBackupService;

    @PostMapping("/create/{username}")
    public ResponseEntity<UserBackupDTO> createAccountBackup(@PathVariable String username, @RequestBody UserBackupDTO backupDTO) {
        User user = service.findUserByUsername(username);
        UserBackup backup = createService.createBackup(backupDTO, user);

        return ResponseEntity.ok(new UserBackupDTO(backup));
    }

    @GetMapping("/latestBackup/{userId}")
    public ResponseEntity<UserBackupDTO> latestBackup(@PathVariable Long userId, LocalDateTime lastModified) {
        UserBackup latestBackup = latestBackupService.latestBackup(userId, lastModified);
        log.info("Backup mais recente enviado com sucesso para o usuário {}", userId);
        return ResponseEntity.ok(new UserBackupDTO(latestBackup));
    }

    @GetMapping
    public ResponseEntity<List<UserBackupDTO>> getAllBackups() {
        List<UserBackup> backups = service.getAllBackups();
        log.info("Lista de backups enviada com sucesso");
        return ResponseEntity.ok(backups.stream()
                                        .map(UserBackupDTO::new)
                                        .toList());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateBackup(@PathVariable Long userId, @RequestBody UserBackupDTO backupDTO) {
        updateService.updateBackup(backupDTO, userId);

        return ResponseEntity.ok("Backup atualizado com sucesso");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteBackup(@PathVariable Long userId) {
        deleteService.deleteUserBackup(userId);
        log.info("Backup deletado com sucesso para o usuário {}", userId);
        return ResponseEntity.ok("Backup deletado com sucesso");
    }
}
