package com.leandroadal.vortasks.entities.social;

import java.util.ArrayList;
import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.user.UserProgressData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class GroupTask extends Task {

    public GroupTask(TaskDTO data, String author, String editor, String category) {
        super(data);
        this.author = author;
        this.editor = editor;
        this.category = category;
        this.progressData = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id; 

    private String author;
    private String editor; // Quem tem permissão para editar a tarefa
    private String category; // Missão ou tarefa

    @ManyToMany(mappedBy = "groupTasks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserProgressData> progressData; // Participantes
    
}
