package com.leandroadal.vortasks.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.user.UserProgressData;

public interface UserProgressDataRepository extends JpaRepository<UserProgressData, Long> {

    Optional<UserProgressData> findByUserId(Long userId);
}
