package com.leandroadal.vortasks.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.security.UserSS;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private LogUser log;

    public List<User> findAllUsers() {
        return repository.findAll();
    }
    
    public User findUserById(String id) {
        try {
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.userNotFound(id);
            throw e;
        }        
    }

    public User findUserByUsername(String username) {
        try {
            return repository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException(username));
        } catch (ObjectNotFoundException e) {
            log.userByUsername(username);
            throw e;
        }   
    }

    public User findUserByEmail(String email) {
        try {
            return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(email));
        } catch (ObjectNotFoundException e) {
            log.userByEmail(email);
            throw e;
        }   
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
    }
    
    public User save(User user) {
        return repository.save(user);
    }
    
    public void delete(User user) {
        repository.delete(user);
    }
}

