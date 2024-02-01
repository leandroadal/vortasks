package com.leandroadal.vortasks.entities.backup.userprogress;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

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
