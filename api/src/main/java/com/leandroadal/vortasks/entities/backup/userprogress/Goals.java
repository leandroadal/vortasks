package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
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

    private Integer daily;
    private Integer weekly;
    private Integer monthly;
    private Integer dailyGoalProgress;
    private Integer weeklyGoalProgress;
    private Integer monthlyGoalProgress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;

    public Goals(int daily, int weekly, int monthly, int dailyGoalProgress, int weeklyGoalProgress,
    int monthlyGoalProgress, Backup userBackup) {
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
        this.dailyGoalProgress = dailyGoalProgress;
        this.weeklyGoalProgress = weeklyGoalProgress;
        this.monthlyGoalProgress = monthlyGoalProgress;
        this.userBackup = userBackup;
    }
    
}
