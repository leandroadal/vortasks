package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AchievementDTO;

import jakarta.persistence.CascadeType;
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

    @Id // TODO mudar para uuid pois nsera igual então pra pra set o id que vir do cliente
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id; 

    private String title;
    private String description;
    private int xp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(AchievementDTO achievementDTO) {
        this.title = achievementDTO.title();
        this.description = achievementDTO.description();
        this.xp = achievementDTO.xp();
    }

}
