package com.leandroadal.vortasks.repositories.social;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.GroupTask;

public interface GroupTaskRepository extends JpaRepository<GroupTask, String> {

    List<GroupTask> findByUserId(String userId);
}
