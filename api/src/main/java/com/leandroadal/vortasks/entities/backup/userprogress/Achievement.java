package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AchievementCreateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id; 

    private String title;
    private String description;
    private Integer xp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;


    
    public Achievement(AchievementCreateDTO achievementDTO, Backup userBackup) {
        this.title = achievementDTO.title();
        this.description = achievementDTO.description();
        this.xp = achievementDTO.xp();
        this.userBackup = userBackup;
    }


    public Achievement(String title, String description, int xp, Backup userBackup) {
        this.title = title;
        this.description = description;
        this.xp = xp;
        this.userBackup = userBackup;
    }


    public void edit(AchievementDTO achievementDTO) {
        this.title = achievementDTO.title();
        this.description = achievementDTO.description();
        this.xp = achievementDTO.xp();
    }
}
