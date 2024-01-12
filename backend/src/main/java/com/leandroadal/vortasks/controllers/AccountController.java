package com.leandroadal.vortasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.dto.AccountCreateDTO;
import com.leandroadal.vortasks.entities.user.Account;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.AccountRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AccountCreateDTO accountDTO) {
        Account account = accountRepository.findByUsername(accountDTO.getUsername());
        if (account.getPassword().equals(accountDTO.getPassword())) {
            return ResponseEntity.ok("Requisição bem-sucedida!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Requisição bem-sucedida!");
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody AccountCreateDTO accountDTO) {
        Account newAccount = new Account();
        newAccount.setUsername(accountDTO.getUsername());
        newAccount.setEmail(accountDTO.getEmail());
        newAccount.setPassword(accountDTO.getPassword());

        User newUser = new User();
        newUser.setName(accountDTO.getName());
        newUser.setCoins(0.0f);
        newUser.setGems(0.0f);

        newAccount.setUser(newUser);

        accountRepository.save(newAccount);
        return ResponseEntity.ok(newAccount);
    }
    
    @GetMapping("/getAccounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}

