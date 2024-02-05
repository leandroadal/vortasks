package com.leandroadal.vortasks.entities.social;

import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractTaskDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OnlineMissionTasks extends AbstractTask {

    public OnlineMissionTasks(AbstractTaskDTO data) {
        super(data);
    }

    @ManyToOne
    private OnlineMission onlineMission;
}
