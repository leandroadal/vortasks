package com.leandroadal.vortasks.services.auth;

import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.auth.exceptions.UserNotFoundException;

@Service
public class AccountService {

    private final UserRepository accountRepository;

    public AccountService(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public User findById(Long id) throws UserNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new UserNotFoundException("null"));
    }

    public User findByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("null"));
    }

    public User save(User account) {
        return accountRepository.save(account);
    }
    
    
}

