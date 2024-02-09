package com.leandroadal.vortasks.controllers.backup;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.dto.BackupCreateDTO;
import com.leandroadal.vortasks.entities.backup.dto.BackupRequestDTO;
import com.leandroadal.vortasks.entities.backup.dto.BackupResponseDTO;
import com.leandroadal.vortasks.services.backup.BackupService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/account/backup")
public class BackupController {

    @Autowired
    private BackupService service;

    @PostMapping("/create/{username}")
    public ResponseEntity<BackupResponseDTO> createUserBackup(@Valid @RequestBody BackupCreateDTO backupDTO) {
        Backup data = backupDTO.toBackup(new Backup());
        Backup backup = service.createBackup(data);
        log.info("Enviado o Backup {} do Usuário com ID: {}", backup.getId(), backup.getUser().getId());
        return ResponseEntity.ok(new BackupResponseDTO(backup));
    }

    @GetMapping("/{backupId}")
    public ResponseEntity<BackupResponseDTO> latestBackup(@PathVariable String backupId, Instant lastModified) {
        Backup latestBackup = service.latestBackup(backupId, lastModified);
        log.info("Enviando o backup mais recente para o usuário {}", backupId);
        return ResponseEntity.ok(new BackupResponseDTO(latestBackup));
    }

    @GetMapping
    public ResponseEntity<List<BackupResponseDTO>> getAllBackups() {
        List<Backup> backups = service.getAllBackups();
        log.info("Lista de backups enviada com sucesso");
        return ResponseEntity.ok(backups.stream()
                                        .map(BackupResponseDTO::new)
                                        .toList());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BackupResponseDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="4") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
    ) {
        Page<Backup> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<BackupResponseDTO> pageDTO = list.map(pg -> new BackupResponseDTO(pg));

        return ResponseEntity.ok(pageDTO);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<BackupResponseDTO> updateBackup(@PathVariable String backupId, @RequestBody BackupRequestDTO backupDTO) {
        Backup data = backupDTO.toBackup(new Backup());
        data.setId(backupId);
        Backup newBackup = service.updateBackup(data);
        return ResponseEntity.ok(new BackupResponseDTO(newBackup));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteBackup(@PathVariable String backupId) {
        service.deleteUserBackup(backupId);
        return ResponseEntity.noContent().build();
    }

}
