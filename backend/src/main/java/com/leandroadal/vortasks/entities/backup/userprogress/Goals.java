package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.GoalsDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.GoalsCreateDTO;

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
public class Goals {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private float daily;
    private float monthly;
    private Integer dailyGoalProgress;
    private Integer monthlyGoalProgress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;


    
    public Goals(GoalsCreateDTO goalsDTO, Backup backup) {
        this.daily = goalsDTO.daily();
        this.monthly = goalsDTO.monthly();
        this.dailyGoalProgress = goalsDTO.dailyGoalProgress();
        this.monthlyGoalProgress = goalsDTO.monthlyGoalProgress();
        this.userBackup = backup;
    }

    public void edit(GoalsDTO goalsDTO) {
        this.daily = goalsDTO.daily();
        this.monthly = goalsDTO.monthly();
    }

    public Goals(float daily, float monthly, int dailyGoalProgress,
    int monthlyGoalProgress, Backup userBackup) {
        this.daily = daily;
        this.monthly = monthly;
        this.dailyGoalProgress = dailyGoalProgress;
        this.monthlyGoalProgress = monthlyGoalProgress;
        this.userBackup = userBackup;
    }
    
}
