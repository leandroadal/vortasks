package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.userprogress.GoalsDTO;
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
public class Goals {

    public Goals(GoalsDTO data) {
        this.daily = data.daily();
        this.monthly = data.monthly();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private float daily;
    private float monthly;

    @OneToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(GoalsDTO goalsDTO) {
        this.daily = goalsDTO.daily();
        this.monthly = goalsDTO.monthly();
    }
}
