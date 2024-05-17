package com.leandroadal.vortasks.entities.social.tasks;

import java.util.HashSet;
import java.util.Set;

import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class GroupTask extends AbstractTask {

    private String author;
    private String editor; // Quem tem permissão para editar a tarefa

    @JoinTable(
      name = "user_group_task", 
      joinColumns = @JoinColumn(name = "group_task_id"), 
      inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private Set<User> users = new HashSet<>(); // Participantes

}
