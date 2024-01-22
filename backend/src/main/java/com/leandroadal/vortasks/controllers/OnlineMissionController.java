package com.leandroadal.vortasks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.OnlineMissionDTO;
import com.leandroadal.vortasks.entities.social.OnlineMission;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.AccountRepository;
import com.leandroadal.vortasks.services.OnlineMissionService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/social")
public class OnlineMissionController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OnlineMissionService onlineMissionService;

    @GetMapping(value = "/onlineMissions/{accountId}")
    public ResponseEntity<List<OnlineMissionDTO>> onlineMissions(@PathVariable @Positive Long accountId) {
        if (accountId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(onlineMissionService.getOnlineMissionListDTOs(optionalAccount));
    }

    @PostMapping(value = "/addOnlineMissions/{accountId}")
    public ResponseEntity<String> addOnlineMissions(@PathVariable @Positive Long accountId,
            @RequestBody OnlineMissionDTO onlineMissionDTO) {
        if (accountId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A conta com o ID especificado não foi encontrada no sistema.");
        }

        Account account = optionalAccount.get();

        OnlineMission onlineMission = onlineMissionService.addOnlineMission(onlineMissionDTO, account);

        if (onlineMission != null) {
            return ResponseEntity.ok("Missão online criada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar a missão");
        }
    }
}
