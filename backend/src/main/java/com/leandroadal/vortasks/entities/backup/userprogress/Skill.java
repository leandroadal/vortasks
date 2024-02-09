package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.SkillDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.SkillCreateDTO;

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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;
    private float xp;
    private int level;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;


    
    public Skill(String name, float xp, int level, Backup userBackup) {
        this.name = name;
        this.xp = xp;
        this.level = level;
        this.userBackup = userBackup;
    }

    public Skill(SkillCreateDTO skillDTO, Backup userBackup) {
        this.name = skillDTO.name();
        this.xp = skillDTO.xp();
        this.userBackup = userBackup;
    }

    public void edit(SkillDTO skillDTO) {
        this.name = skillDTO.name();
        this.xp = skillDTO.xp();
    }
    
}
