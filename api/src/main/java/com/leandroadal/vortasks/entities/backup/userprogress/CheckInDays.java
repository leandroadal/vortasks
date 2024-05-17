package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.CheckInDaysDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.CheckInDaysCreateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDays {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private int days;
    private int month; // Mês da contagem atual

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;


    
    public CheckInDays(int days, int month, Backup userBackup) {
        this.days = days;
        this.month = month;
        this.userBackup = userBackup;
    }

    public CheckInDays(CheckInDaysCreateDTO checkInDaysDTO, Backup backup) {
        this.days = checkInDaysDTO.days();
        this.month = checkInDaysDTO.month();
        this.userBackup = backup;
    }

    public void edit(CheckInDaysDTO checkInDaysDTO) {
        this.days = checkInDaysDTO.days();
        this.month = checkInDaysDTO.month();
    }
}
