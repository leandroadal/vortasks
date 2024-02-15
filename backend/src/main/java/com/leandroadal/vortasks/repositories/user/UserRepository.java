package com.leandroadal.vortasks.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.user.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
}