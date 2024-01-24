package com.leandroadal.vortasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.user.UserProgressData;

public interface UserProgressDataRepository extends JpaRepository<UserProgressData, Long> {

}
