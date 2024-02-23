package com.leandroadal.vortasks.repositories.social;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;

public interface GroupTaskRepository extends JpaRepository<GroupTask, String> {

    // List<GroupTask> findByUsersId(String userId);

    Page<GroupTask> findDistinctByNameContainingAndStatusAndTypeAndUsersId(String name,
            Status status, String userId, Type type, PageRequest pageRequest);

    Page<GroupTask> findDistinctByNameContainingAndStatusAndUsersId(String name,
            Status status, String userId, PageRequest pageRequest);

    // Page<GroupTask>
    // findDistinctByNameContainingAndStatusContainingAndTypeContaining(
    // String name, String status, String type, PageRequest pageRequest);
}
