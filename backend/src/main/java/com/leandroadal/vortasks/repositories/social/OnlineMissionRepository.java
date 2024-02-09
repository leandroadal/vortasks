package com.leandroadal.vortasks.repositories.social;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.OnlineMission;

public interface OnlineMissionRepository extends JpaRepository<OnlineMission, String> {

    List<OnlineMission> findByUserId(String userId);
}
