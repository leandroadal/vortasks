package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractTaskCreateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
public abstract class AbstractTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // Concluído, falha ou em andamento
    private int xp;
    private int coins; // moeda

    @Enumerated(EnumType.STRING)
    private Type type; // Lazer ou Atividade
    private int repetition; // Diária(1), semanal(7) ou mensal(30)
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease;

    
    public AbstractTask(String name, String description, Status status, int xp, int coins, Type type, int repetition,
            String reminder, int skillIncrease, int skillDecrease) {
        this.name = name;
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
    
    public AbstractTask(AbstractTaskCreateDTO taskDTO) {
        //TODO Auto-generated constructor stub
    }
}
