package com.leandroadal.vortasks.entities.social.tasks;

import java.time.Instant;


import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OnlineMissionTasks extends AbstractTask {

    @ManyToOne(cascade = CascadeType.ALL)
    private OnlineMission onlineMission;


    // ------------------ Construtores ------------------
    public OnlineMissionTasks(String id, String title, String description, Status status, Integer xp, Integer coins,
            Type type, Integer repetition, Instant reminder, Integer skillIncrease, Integer skillDecrease,
            Instant startDate, Instant endDate, Theme theme, Difficulty difficulty, OnlineMission onlineMission) {
        super(id, title, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease,
                startDate, endDate, theme, difficulty);
        this.onlineMission = onlineMission;
    }

}
