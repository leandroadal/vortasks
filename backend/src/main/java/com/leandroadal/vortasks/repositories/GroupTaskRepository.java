package com.leandroadal.vortasks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.GroupTask;

public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {

    List<GroupTask> findByProgressDataId(Long userId);
}
