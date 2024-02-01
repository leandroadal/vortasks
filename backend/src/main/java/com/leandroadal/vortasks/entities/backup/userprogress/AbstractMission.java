package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.social.OnlineMissionDTO;
import com.leandroadal.vortasks.dto.userprogress.MissionDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractMission {

    public AbstractMission(MissionDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.status = data.status();
        this.xp = data.xp();
        this.coins = data.coins();
        this.type = data.type();
        this.repetition = data.repetition();
        this.reminder = data.reminder();
        this.skillIncrease = data.skillIncrease();
        this.skillDecrease = data.skillDecrease();
    }
    
    public AbstractMission(OnlineMissionDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.status = data.status();
        this.xp = data.xp();
        this.coins = data.coins();
        this.type = data.type();
        this.repetition = data.repetition();
        this.reminder = data.reminder();
        this.skillIncrease = data.skillIncrease();
        this.skillDecrease = data.skillDecrease();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;
    private String description;
    private String status; // Concluído, falha ou em andamento
    private int xp; // xp
    private int coins;
    private String type; // Lazer  ou atividade
    private String repetition;
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease; // diminuir xp da skill
}
