package com.leandroadal.vortasks.repositories.backup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;

public interface CheckInDaysRepository extends JpaRepository<CheckInDays, String> {

}
