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
import jakarta.validation.constraints.NotNull;
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
public abstract class AbstractMission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // Concluído, falha ou em andamento

    private Integer xp; // xp
    private Integer coins;

    @Enumerated(EnumType.STRING)
    private Type type; // Lazer  ou atividade

    private String repetition;
    private String reminder;
    private Integer skillIncrease; // Aumentar xp da skill 
    private Integer skillDecrease; // diminuir xp da skill
    private Instant startDate;
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

}
