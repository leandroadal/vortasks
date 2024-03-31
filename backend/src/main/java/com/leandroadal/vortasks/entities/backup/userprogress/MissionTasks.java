package com.leandroadal.vortasks.entities.backup.userprogress;

import java.time.Instant;

import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MissionTasks extends AbstractMission{

    @ManyToOne(cascade = CascadeType.ALL)
    private Mission mission;

    public MissionTasks(String id, String title, String description, Status status, Integer xp, Integer coins,
            Type type, Integer repetition, Instant reminder, Integer skillIncrease, Integer skillDecrease,
            Instant startDate, Instant endDate, Theme theme, Difficulty difficulty, Mission mission) {
        super(id, title, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease,
                startDate, endDate, theme, difficulty);
        this.mission = mission;
    }

}
