package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractTaskCreateDTO;

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



    public Task(AbstractTaskCreateDTO taskDTO, Backup userBackup2) {
        //TODO Auto-generated constructor stub
    }
    
    public Task(String id, String name, String description, Status status, int xp, int coins, Type type, int repetition,
            String reminder, int skillIncrease, int skillDecrease, Backup userBackup) {
        super(id, name, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.userBackup = userBackup;
    }

    public Task(String name, String description, Status status, int xp, int coins, Type type, int repetition,
            String reminder, int skillIncrease, int skillDecrease, Backup userBackup) {
        super(name, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.userBackup = userBackup;
    }

}
