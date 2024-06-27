package com.leandroadal.vortasks.controllers.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.controllers.backup.doc.BackupSwagger;
import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.dto.BackupCreateDTO;
import com.leandroadal.vortasks.entities.backup.dto.BackupRequestDTO;
import com.leandroadal.vortasks.entities.backup.dto.BackupResponseDTO;
import com.leandroadal.vortasks.services.backup.BackupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user/backup")
public class BackupController {

    @Autowired
    private BackupService service;

    @GetMapping
    @BackupSwagger.GetBackupSwagger
    public ResponseEntity<BackupResponseDTO> getBackup() {
        Backup latestBackup = service.getBackup();
        log.info("Enviando o backup mais recente para o usuário {}", latestBackup.getUser().getId());
        return ResponseEntity.ok(new BackupResponseDTO(latestBackup));
    }

    @GetMapping("/latest")
    @BackupSwagger.LatestBackupSwagger
    public ResponseEntity<BackupResponseDTO> latestBackup(@RequestParam String lastModified) {
        Backup latestBackup = service.latestBackup(lastModified);
        log.info("Enviando o backup mais recente para o usuário {}", latestBackup.getUser().getId());
        return ResponseEntity.ok(new BackupResponseDTO(latestBackup));
    }

    @PostMapping("/create")
    @BackupSwagger.CreateBackupSwagger
    public ResponseEntity<BackupResponseDTO> createUserBackup(@RequestBody BackupCreateDTO backupDTO) {
        Backup data = backupDTO.toBackup(new Backup());
        Backup backup = service.createBackup(data);
        log.info("Enviado o Backup {} do Usuário com ID: {}", backup.getId(), backup.getUser().getId());
        return ResponseEntity.ok(new BackupResponseDTO(backup));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/page")
    @BackupSwagger.FindPageBackupSwagger
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

    @PutMapping
    @BackupSwagger.UpdateBackupSwagger
    public ResponseEntity<BackupResponseDTO> updateBackup(@RequestBody BackupRequestDTO backupDTO) {
        Backup data = backupDTO.toBackup(new Backup());
        Backup newBackup = service.updateBackup(data);
        return ResponseEntity.ok(new BackupResponseDTO(newBackup));
    }

    @DeleteMapping
    @BackupSwagger.DeleteBackupSwagger
    public ResponseEntity<Void> deleteBackup() {
        service.deleteUserBackup();
        return ResponseEntity.noContent().build();
    }

}
