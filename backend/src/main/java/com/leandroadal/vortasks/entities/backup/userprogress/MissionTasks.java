package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class MissionTasks extends AbstractTask{

    public MissionTasks(AbstractTaskDTO data, Mission mission) {
        super(data);
        this.mission = mission;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Mission mission;

}
