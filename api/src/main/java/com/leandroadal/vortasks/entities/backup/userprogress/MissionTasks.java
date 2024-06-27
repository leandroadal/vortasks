package com.leandroadal.vortasks.entities.backup.userprogress;

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
    private Mission mission; // Precisa de uma referencia a missão

}
