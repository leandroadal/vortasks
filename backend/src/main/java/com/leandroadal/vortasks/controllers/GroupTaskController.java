package com.leandroadal.vortasks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.GroupTaskDTO;
import com.leandroadal.vortasks.entities.social.GroupTask;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.AccountRepository;
import com.leandroadal.vortasks.services.GroupTaskService;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/social")
public class GroupTaskController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupTaskService groupTaskService;

    @GetMapping(value = "/groupTasks/{accountId}")
    public ResponseEntity<List<GroupTaskDTO>> groupTasks(@PathVariable @Positive Long accountId) {
        if (accountId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            List<GroupTask> groupTask = groupTaskService.getGroupTaskList(account);
            List<GroupTaskDTO> groupTaskDTOs = groupTaskService.mapToGroupTaskDTO(groupTask);
            return ResponseEntity.ok(groupTaskDTOs);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/addGroupTasks/{accountId}")
    public ResponseEntity<String> addGroupTasks(@PathVariable @Positive Long accountId, @RequestBody GroupTaskDTO groupTaskDTO) {
        
        GroupTask groupTask = groupTaskService.addGroupTask(groupTaskDTO);

        if (groupTask != null) {
            return ResponseEntity.ok("Tarefa em grupo criada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar a tarefa em grupo");
        }
    }

    @PutMapping("editGroupTask/{groupTaskId}")
    public ResponseEntity<String> editGroupTask(@PathVariable Long groupTaskId, @RequestBody GroupTaskDTO groupTaskDTO) {
        GroupTask groupTask = groupTaskService.editGroupTask(groupTaskId, groupTaskDTO);

        if (groupTask != null) {
            return ResponseEntity.ok("Tarefa em grupo edita com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao editar a Tarefa em grupo");
        }
    }
}
