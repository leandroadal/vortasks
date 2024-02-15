package com.leandroadal.vortasks.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findUserById(String id) {
        try {
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            //log.userNotFound(id);
            log.error("Usuário com ID: {} não foi encontrado!", id);
            throw e;
        }        
    }

    public User findUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException(username));
    }

    public User save(User user) {
        return repository.save(user);
    }
    
    public void delete(User user) {
        repository.delete(user);
    }
}

