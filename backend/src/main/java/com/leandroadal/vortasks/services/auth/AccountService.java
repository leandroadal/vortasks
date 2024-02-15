package com.leandroadal.vortasks.services.auth;

import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;

@Service
public class AccountService {

    private final UserRepository accountRepository;

    public AccountService(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public User findById(String id) {
        
        return accountRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow();
    }

    public User save(User account) {
        return accountRepository.save(account);
    }
    
    
}

