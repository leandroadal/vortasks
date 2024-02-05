package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;

import jakarta.persistence.CascadeType;
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

    public CheckInDays(CheckInDaysDTO data, UserBackup backup) {
        this.days = data.days();
        this.month = data.month();
        this.userBackup = backup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private int days;
    private int month; // Mês da contagem atual

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(CheckInDaysDTO checkInDaysDTO) {
        this.days = checkInDaysDTO.days();
        this.month = checkInDaysDTO.month();
    }
}
