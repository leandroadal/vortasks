package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.social.dto.OnlineMissionDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractMission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @NotNull
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // Concluído, falha ou em andamento

    private int xp; // xp
    private int coins;

    @Enumerated(EnumType.STRING)
    private Type type; // Lazer  ou atividade

    private String repetition;
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease; // diminuir xp da skill

        
    public AbstractMission(@NotNull String title, String description, Status status, int xp, int coins, Type type,
            String repetition, String reminder, int skillIncrease, int skillDecrease) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.xp = xp;
        this.coins = coins;
        this.type = type;
        this.repetition = repetition;
        this.reminder = reminder;
        this.skillIncrease = skillIncrease;
        this.skillDecrease = skillDecrease;
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
}
