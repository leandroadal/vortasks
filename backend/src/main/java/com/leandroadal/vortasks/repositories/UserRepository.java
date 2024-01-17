package com.leandroadal.vortasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
