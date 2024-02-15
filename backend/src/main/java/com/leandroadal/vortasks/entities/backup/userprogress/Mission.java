package com.leandroadal.vortasks.entities.backup.userprogress;

import java.time.Instant;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mission extends AbstractMission {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionTasks> requirements; // tarefas necessárias para concluir a missão

    public Mission(String id, String title, String description, Status status, Integer xp, Integer coins, Type type,
            String repetition, String reminder, Integer skillIncrease, Integer skillDecrease, Instant startDate,
            Instant endDate, Theme theme, Difficulty difficulty, Backup userBackup, List<MissionTasks> requirements) {
        super(id, title, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease,
                startDate, endDate, theme, difficulty);
        this.userBackup = userBackup;
        this.requirements = requirements;
    }

}
