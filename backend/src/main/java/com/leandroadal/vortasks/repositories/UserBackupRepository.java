package com.leandroadal.vortasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.UserBackup;

public interface UserBackupRepository extends JpaRepository<UserBackup, Long> {

    UserBackup findByProgressDataId(Long userId);

}
