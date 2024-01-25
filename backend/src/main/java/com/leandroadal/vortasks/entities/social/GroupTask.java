package com.leandroadal.vortasks.entities.social;

import java.util.ArrayList;
import java.util.List;

import com.leandroadal.vortasks.dto.social.GroupTaskDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.AbstractTask;
import com.leandroadal.vortasks.entities.user.UserProgressData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class GroupTask extends AbstractTask {

    public GroupTask(GroupTaskDTO data) {
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
        this.author = data.author();
        this.editor = data.editor();
        this.category = data.category();
        this.progressData = new ArrayList<>();
    }

    private String author;
    private String editor; // Quem tem permissão para editar a tarefa
    private String category; // Missão ou tarefa

    @ManyToMany(mappedBy = "groupTasks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserProgressData> progressData; // Participantes
    
}
