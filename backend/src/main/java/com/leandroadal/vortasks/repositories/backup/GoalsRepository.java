package com.leandroadal.vortasks.repositories.backup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.userprogress.Goals;

public interface GoalsRepository extends JpaRepository<Goals, String> {

}
