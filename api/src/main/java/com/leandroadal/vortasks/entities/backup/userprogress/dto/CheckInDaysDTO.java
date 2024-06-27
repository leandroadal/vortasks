package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;

public record CheckInDaysDTO(String id, int days, int month) {

    public CheckInDaysDTO(CheckInDays checkInDays) {
        this(
            checkInDays.getId(),
            checkInDays.getDays(),
            checkInDays.getMonth() 
        );
    }

    public CheckInDays toCheckInDays(Backup backup) {
        return new CheckInDays(
            id,
            days,
            month,
            backup
        );
    }
}
