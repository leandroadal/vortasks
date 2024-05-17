package com.leandroadal.vortasks.entities.backup.userprogress;

import java.time.Instant;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task extends AbstractTask {  

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;




    public Task(String id, String name, String description, Status status, Integer xp, Integer coins, Type type,
            Integer repetition, Instant reminder, Integer skillIncrease, Integer skillDecrease, Instant startDate,
            Instant endDate, Theme theme, Difficulty difficulty, Backup userBackup) {
        super(id, name, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease,
                startDate, endDate, theme, difficulty);
        this.userBackup = userBackup;
    }
    
}
