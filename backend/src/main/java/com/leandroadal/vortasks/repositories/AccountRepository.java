package com.leandroadal.vortasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.user.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
