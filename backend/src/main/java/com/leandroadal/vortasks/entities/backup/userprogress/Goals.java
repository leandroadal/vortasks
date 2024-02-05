package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;

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
public class Goals {

    public Goals(GoalsDTO data, UserBackup backup) {
        this.daily = data.daily();
        this.monthly = data.monthly();
        this.userBackup = backup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private float daily;
    private float monthly;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(GoalsDTO goalsDTO) {
        this.daily = goalsDTO.daily();
        this.monthly = goalsDTO.monthly();
    }
}
