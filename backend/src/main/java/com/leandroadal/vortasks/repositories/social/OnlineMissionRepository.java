package com.leandroadal.vortasks.repositories.social;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;

public interface OnlineMissionRepository extends JpaRepository<OnlineMission, String> {

    List<OnlineMission> findByUserId(String userId);

    Page<OnlineMission> findDistinctByTitleContainingAndStatus(String title, Status status, PageRequest pageRequest);

    Page<OnlineMission> findDistinctByTitleContainingAndStatusAndType(String title, Status status, Type type,
            PageRequest pageRequest);

    Page<OnlineMission> findDistinctByTitleContainingAndStatusAndUserId(String title, Status status, String userId,
            PageRequest pageRequest);

    Page<OnlineMission> findDistinctByTitleContainingAndStatusAndTypeAndUserId(String title, Status status, Type type,
            String userId, PageRequest pageRequest);
}
