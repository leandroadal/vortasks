package com.leandroadal.vortasks.entities.social.tasks;

import java.util.ArrayList;
import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OnlineMission extends AbstractTask {

    private Integer likes;
    
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "onlineMission", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<OnlineMissionTasks> requirements = new ArrayList<>(); // tarefas necessárias para concluir a missão


}
