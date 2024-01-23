package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.shop.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CheckInDays {

    public CheckInDays(CheckInDaysDTO data) {
        this.days = data.days();
        this.month = data.month();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    private int days;
    private int month; // Mês da contagem atual

}
