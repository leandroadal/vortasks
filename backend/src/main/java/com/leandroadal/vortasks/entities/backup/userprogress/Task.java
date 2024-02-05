package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

import jakarta.persistence.CascadeType;
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

    public Task(AbstractTaskDTO data, UserBackup userBackup) {
        super(data);
        this.userBackup = userBackup;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    public void edit(AbstractTaskDTO data) {
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
}
