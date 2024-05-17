package com.leandroadal.vortasks.repositories.backup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.userprogress.Mission;

public interface MissionRepository extends JpaRepository<Mission, String> {

}
