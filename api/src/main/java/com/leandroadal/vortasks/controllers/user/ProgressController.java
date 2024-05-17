package com.leandroadal.vortasks.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.user.ProgressData;
import com.leandroadal.vortasks.entities.user.dto.ProgressDataRequestDTO;
import com.leandroadal.vortasks.entities.user.dto.ProgressDataResponseDTO;
import com.leandroadal.vortasks.services.user.ProgressDataService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/user/progress")
public class ProgressController {

    @Autowired
    private ProgressDataService service;
    
    @GetMapping("{id}")
    public ResponseEntity<ProgressDataResponseDTO> getProgress(@PathVariable String id) {
        ProgressData progress = service.findProgressById(id);
        return ResponseEntity.ok(new ProgressDataResponseDTO(progress));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ProgressDataResponseDTO> updateProgress(@PathVariable String id, @Valid @RequestBody ProgressDataRequestDTO data) {
        ProgressData progress = service.editProgress(data.toProgressData(id));
        return ResponseEntity.ok(new ProgressDataResponseDTO(progress));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping
    public ResponseEntity<ProgressDataResponseDTO> updatePartialProgress(@PathVariable String id, @RequestBody ProgressDataRequestDTO data) {
        ProgressData progress = service.partialEditProgress(data.toProgressData(id));
        return ResponseEntity.ok(new ProgressDataResponseDTO(progress));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProgress() {
        service.deleteProgress();
        return ResponseEntity.noContent().build();
    }
}
