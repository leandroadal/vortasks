package com.leandroadal.vortasks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.OnlineMission;

public interface OnlineMissionRepository extends JpaRepository<OnlineMission, Long> {

    List<OnlineMission> findByProgressDataId(Long userId);
}
