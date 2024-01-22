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

import com.leandroadal.vortasks.dto.FriendDTO;
import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.repositories.AccountRepository;
import com.leandroadal.vortasks.services.FriendService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/social")
public class FriendController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FriendService socialService;

    @GetMapping(value = "/friends/{accountId}")
    public ResponseEntity<List<FriendDTO>> getFriends(@PathVariable Long accountId) {
        if (accountId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(socialService.getFriendListDTOs(account));
    }

    @PostMapping(value = "/addFriends/{accountId}")
    public ResponseEntity<String> addFriends(@PathVariable @Positive Long accountId,
            @Valid @RequestBody FriendDTO friendDTO) {
        if (accountId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A conta com o ID especificado não foi encontrada no sistema.");
        }
        Account account = optionalAccount.get();

        Friend friend = socialService.addFriend(friendDTO, account);

        if (friend != null) {
            return ResponseEntity.ok("amigo criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar o backup");
        }
    }

}
