package com.leandroadal.vortasks.controllers.social;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMissionTasks;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.create.OnlineMissionCreateDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.create.OnlineMissionTasksCreateDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.request.OnlineMissionRequestDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.request.OnlineMissionTasksRequestDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.response.OnlineMissionResponseDTO;
import com.leandroadal.vortasks.services.social.tasks.OnlineMissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/social/onlineMissions")
public class OnlineMissionController {

    @Autowired
    private OnlineMissionService service;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<OnlineMissionResponseDTO> onlineMissions(@PathVariable @UUID String id) {
        OnlineMission mission = service.findOnlineMissionById(id);
        return ResponseEntity.ok(new OnlineMissionResponseDTO(mission));
    }

    @GetMapping
    public ResponseEntity<List<OnlineMissionResponseDTO>> allOnlineMissions() {
        List<OnlineMission> mission = service.findAll();
        return ResponseEntity.ok(mission.stream()
                                        .map(OnlineMissionResponseDTO::new)
                                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<OnlineMissionResponseDTO>> findPage(
            @PathVariable @UUID String id,
            @RequestParam(value ="title", defaultValue = "") String title,
            @RequestParam(value = "status", defaultValue = "IN_PROGRESS") Status status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "type", required = false) Type type,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "status") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<OnlineMission> list = service.search(title, status, type, page, linesPerPage, direction, orderBy);
        Page<OnlineMissionResponseDTO> listDto = list.map(obj -> new OnlineMissionResponseDTO(obj));
        return ResponseEntity.ok(listDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OnlineMissionResponseDTO> addOnlineMissions(@PathVariable @UUID String userId, @Valid @RequestBody OnlineMissionCreateDTO onlineMissionDTO) {
        OnlineMission onlineMission = service.addOnlineMission(userId, onlineMissionDTO.toOnlineMission());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/social/onlineMissions/{id}")
                .buildAndExpand(onlineMission.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new OnlineMissionResponseDTO(onlineMission));
    }  

    @PutMapping(value = "/{onlineMissionId}")
    public ResponseEntity<OnlineMissionResponseDTO> edit(@PathVariable @UUID String onlineMissionId, @Valid @RequestBody OnlineMissionRequestDTO onlineMissionDTO) {
        OnlineMission 
        mission = service.edit(onlineMissionDTO.toOnlineMission(onlineMissionId));
        return ResponseEntity.ok(new OnlineMissionResponseDTO(mission));
    }

    @PatchMapping(value = "/{onlineMissionId}")
    public ResponseEntity<OnlineMissionResponseDTO> partialEdit(@PathVariable @UUID String onlineMissionId, @Valid @RequestBody OnlineMissionRequestDTO onlineMissionDTO) {
        OnlineMission mission = onlineMissionDTO.toOnlineMission(onlineMissionId);
        mission = service.partialEdit(mission);
        return ResponseEntity.ok(new OnlineMissionResponseDTO(mission));
    }

    @DeleteMapping(value = "/{onlineMissionId}")
    public ResponseEntity<OnlineMissionResponseDTO> delete(@PathVariable @UUID String onlineMissionId) {
        service.deleteOnlineMission(onlineMissionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{onlineMissionId}/requirements")
    public ResponseEntity<OnlineMissionResponseDTO> addToRequirements(@PathVariable @UUID String onlineMissionId, @Valid @RequestBody List<OnlineMissionTasksCreateDTO> onlineMissionDTO) {
        List<OnlineMissionTasks> missionTasks = onlineMissionDTO.stream().map(dto -> dto.toOnlineMissionTasks()).collect(Collectors.toList());
        OnlineMission mission = service.addToRequirements(onlineMissionId, missionTasks);
        return ResponseEntity.ok(new OnlineMissionResponseDTO(mission));
    }

    @PutMapping(value = "/{onlineMissionId}/requirements/")
    public ResponseEntity<OnlineMissionResponseDTO> editRequirements(@UUID @PathVariable String onlineMissionId, String id, @Valid @RequestBody Set<OnlineMissionTasksRequestDTO> onlineMissionDTO) {
        List<OnlineMissionTasks> data = onlineMissionDTO.stream().map(dto -> dto.toOnlineMissionTasks()).collect(Collectors.toList());

        OnlineMission mission = service.editRequirements(onlineMissionId, data);
        return ResponseEntity.ok(new OnlineMissionResponseDTO(mission));
    }

    @DeleteMapping(value = "/{onlineMissionId}/requirements/{id}")
    public ResponseEntity<OnlineMissionResponseDTO> removeFromRequirements(@UUID @PathVariable String onlineMissionId, @PathVariable String id) {
        service.removeFromRequirements(onlineMissionId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{onlineMissionId}/requirements/clear")
    public ResponseEntity<OnlineMissionResponseDTO> clearRequirements(@PathVariable @UUID String onlineMissionId) {
        service.clearRequirements(onlineMissionId);
        return ResponseEntity.noContent().build();
    }
}
