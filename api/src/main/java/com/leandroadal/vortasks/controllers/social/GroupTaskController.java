package com.leandroadal.vortasks.controllers.social;

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

import com.leandroadal.vortasks.controllers.social.doc.GroupTaskDocSwagger;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;
import com.leandroadal.vortasks.entities.social.tasks.dto.grouptask.GroupTaskRequestDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.grouptask.GroupTaskResponseDTO;
import com.leandroadal.vortasks.entities.social.tasks.dto.grouptask.create.GroupTaskCreateDTO;
import com.leandroadal.vortasks.services.social.tasks.GroupTaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/social/groupTasks")
public class GroupTaskController {

    @Autowired
    private GroupTaskService service;


    @GetMapping("{id}")
    @GroupTaskDocSwagger.GetGroupTaskSwagger
    public ResponseEntity<GroupTaskResponseDTO> getGroupTask(@PathVariable @UUID String id) {
        GroupTask groupTask = service.finGroupTask(id);
        return ResponseEntity.ok(new GroupTaskResponseDTO(groupTask));
    }

    @GetMapping("/search")
    @GroupTaskDocSwagger.FindPageGroupTaskSwagger
    public ResponseEntity<Page<GroupTaskResponseDTO>> findPage(
        @RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "status", defaultValue = "IN_PROGRESS") Status status,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "type", required = false) Type type,
        @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "status") String orderBy) {

        Page<GroupTask> list = service.search(name, status, type, page, linesPerPage, direction, orderBy);
        Page<GroupTaskResponseDTO> listDto = list.map(obj -> new GroupTaskResponseDTO(obj));

        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    @GroupTaskDocSwagger.AddGroupTaskSwagger
    public ResponseEntity<GroupTaskResponseDTO> addGroupTasks(@Valid @RequestBody GroupTaskCreateDTO groupTaskDTO) {
        GroupTask groupTask = service.addGroupTask(groupTaskDTO.toGroupTask(), groupTaskDTO.usernames());

        return ResponseEntity.ok(new GroupTaskResponseDTO(groupTask));
    }

    @PutMapping("/{groupTaskId}")
    @GroupTaskDocSwagger.EditGroupTaskSwagger
    public ResponseEntity<GroupTaskResponseDTO> editGroupTask(@PathVariable @UUID String groupTaskId, @Valid @RequestBody GroupTaskRequestDTO groupTaskDTO) {
        GroupTask groupTask = service.editGroupTask(groupTaskDTO.toGroupTask(groupTaskId));

        return ResponseEntity.ok(new GroupTaskResponseDTO(groupTask));
    }

    @PatchMapping("/{groupTaskId}")
    @GroupTaskDocSwagger.PartialEditGroupTaskSwagger
    public ResponseEntity<GroupTaskResponseDTO> partialEditGroupTask(@PathVariable @UUID String groupTaskId, @RequestBody GroupTaskRequestDTO groupTaskDTO) {
        GroupTask groupTask = service.partialEditGroupTask(groupTaskDTO.toGroupTask(groupTaskId));

        return ResponseEntity.ok(new GroupTaskResponseDTO(groupTask));
    }

    @DeleteMapping("/{groupTaskId}")
    @GroupTaskDocSwagger.DeleteGroupTaskSwagger
    public ResponseEntity<String> deleteGroupTask(@PathVariable @UUID String groupTaskId) {
        service.deleteGroupTask(groupTaskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{groupTaskId}/participants/{userId}")
    @GroupTaskDocSwagger.AddUserToGroupTaskSwagger
    public ResponseEntity<String> addUserToGroupTask(@PathVariable @UUID String groupTaskId, @PathVariable @UUID String userId) {
        service.addUser(groupTaskId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupTaskId}/participants/{userId}")
    @GroupTaskDocSwagger.RemoveUserFromGroupTaskSwagger
    public ResponseEntity<String> removeUserFromGroupTask(@PathVariable @UUID String groupTaskId, @PathVariable @UUID String userId) {
        service.deleteUser(groupTaskId, userId);
        return ResponseEntity.noContent().build();
    }


}
