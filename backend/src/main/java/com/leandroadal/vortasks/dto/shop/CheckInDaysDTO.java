package com.leandroadal.vortasks.dto.shop;

import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;

public record CheckInDaysDTO(int days, int month) {

    public CheckInDaysDTO(CheckInDays checkInDays) {
        this(
            checkInDays.getDays(),
            checkInDays.getMonth() 
        );
       
    }
}
