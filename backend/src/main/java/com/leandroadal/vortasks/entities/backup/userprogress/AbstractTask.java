package com.leandroadal.vortasks.entities.backup.userprogress;

import java.time.Instant;

import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // Concluído, falha ou em andamento
    private Integer xp;
    private Integer coins; // moeda

    @Enumerated(EnumType.STRING)
    private Type type; // Lazer ou Atividade
    private Integer repetition; // Diária(1), semanal(7) ou mensal(30)
    private Instant reminder;
    private Integer skillIncrease; // Aumentar xp da skill 
    private Integer skillDecrease;
    private Instant startDate;
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
