package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;

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
public class Skill {

    public Skill(SkillDTO data, UserBackup userBackup) {
        this.name = data.name();
        this.xp = data.xp();
        this.userBackup = userBackup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;
    private float xp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(SkillDTO skillDTO) {
        this.name = skillDTO.name();
        this.xp = skillDTO.xp();
    }

    
}
