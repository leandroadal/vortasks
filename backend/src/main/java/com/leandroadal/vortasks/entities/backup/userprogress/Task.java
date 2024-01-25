package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.social.OnlineMission;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task extends AbstractTask {

    public Task(TaskDTO data) {
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

    @ManyToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;
    
    @ManyToOne(optional = true)
    private Mission mission; // Pode ou não estar associada a uma missão

    @ManyToOne
    private OnlineMission onlineMission; // Pode ou não estar associada a uma missão online

}
