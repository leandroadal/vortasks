package com.leandroadal.vortasks.entities.backup.userprogress;

import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mission extends AbstractMission {

    public Mission(MissionDTO data, UserBackup userBackup) {
        this.setTitle(data.title());
        this.setDescription(data.description());
        this.setStatus(data.status());
        this.setXp(data.xp());
        this.setCoins(data.coins());
        this.setType(data.type());
        this.setRepetition(data.repetition());
        this.setReminder(data.reminder());
        this.setSkillIncrease(data.skillIncrease());
        this.setSkillDecrease(data.skillDecrease());
        this.userBackup = userBackup;
    }

    @ManyToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Task> requirements; // tarefas necessárias para concluir a missão
 
}
