package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;

public record CheckInDaysCreateDTO(
        int days,
        int month) {

    public CheckInDaysCreateDTO(int days, int month) {
        this.days = days;
        this.month = month;
    }

    public CheckInDays toCheckInDays(Backup backup) {
        CheckInDays checkInDays = new CheckInDays();
        checkInDays.setDays(days);
        checkInDays.setMonth(month);
        checkInDays.setUserBackup(backup);
        return checkInDays;
    }
}
