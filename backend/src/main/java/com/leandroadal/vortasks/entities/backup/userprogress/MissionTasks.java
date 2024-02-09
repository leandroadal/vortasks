package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractTaskCreateDTO;

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
public class MissionTasks extends AbstractTask{

    @ManyToOne(cascade = CascadeType.ALL)
    private Mission mission;


    
    
    public MissionTasks(String name, String description, Status status, int xp, int coins, Type type, int repetition,
            String reminder, int skillIncrease, int skillDecrease, Mission mission) {
        super(name, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.mission = mission;
    }

    public MissionTasks(AbstractTaskCreateDTO taskDTO, Mission mission) {
        super(taskDTO);
        this.mission = mission;
    }

    public MissionTasks(String id, String name, String description, Status status, int xp, int coins, Type type,
            int repetition, String reminder, int skillIncrease, int skillDecrease, Mission mission) {
        super(id, name, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.mission = mission;
    }


    

}
