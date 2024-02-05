package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTask {

    public AbstractTask(AbstractTaskDTO data) {
        this.setStatus(data.status());
        this.setName(data.name());
        this.setDescription(data.description());
        this.setXp(data.xp());
        this.setCoins(data.coins());
        this.setType(data.type());
        this.setRepetition(data.repetition());
        this.setReminder(data.reminder());
        this.setSkillIncrease(data.skillIncrease());
        this.setSkillDecrease(data.skillDecrease());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    private String status; // Concluído, falha ou em andamento
    private String name;
    private String description;
    private int xp;
    private int coins; // moeda
    private String type; // Lazer ou Atividade
    private int repetition; // Diária(1), semanal(7) ou mensal(30)
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease;
}
