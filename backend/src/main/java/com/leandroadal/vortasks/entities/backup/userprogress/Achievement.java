package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.userprogress.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Achievement {

    public Achievement(AchievementDTO data, UserBackup userBackup) {
        this.title = data.title();
        this.description = data.description();
        this.xp = data.xp();
        this.userBackup = userBackup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id; 

    private String title;
    private String description;
    private int xp;

    @ManyToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(AchievementDTO achievementDTO) {
        this.title = achievementDTO.title();
        this.description = achievementDTO.description();
        this.xp = achievementDTO.xp();
    }

}
