package com.leandroadal.vortasks.repositories.backup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.UserBackup;

public interface UserBackupRepository extends JpaRepository<UserBackup, String> {

    Optional<UserBackup> findByProgressDataId(Long userId);


}
