package com.leandroadal.vortasks.entities.social;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.AbstractMission;
import com.leandroadal.vortasks.entities.social.dto.OnlineMissionDTO;
import com.leandroadal.vortasks.entities.user.UserProgressData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OnlineMission extends AbstractMission {

    public OnlineMission(OnlineMissionDTO data, UserProgressData dataProgress, List<OnlineMissionTasks> requirements) {
        this.setTitle(data.title());
        this.setDescription(data.description());
        this.setStatus(data.status());
        this.setXp(data.xp());
        this.setCoins(data.coins());
        this.setType(data.type());
        this.setRepetition(data.repetition());
        this.setReminder(data.reminder());
        this.setSkillIncrease(data.skillIncrease());
        this.setSkillDecrease(data.skillDecrease());
        this.likes = data.likes();
        this.progressData = dataProgress;
        this.requirements = requirements;
    }

    private int likes;
    
    @ManyToOne
    private UserProgressData progressData;

    @OneToMany(mappedBy = "onlineMission", cascade = CascadeType.ALL)
    private List<OnlineMissionTasks> requirements; // tarefas necessárias para concluir a missão

    public void edit(OnlineMissionDTO data) {
        this.setTitle(data.title());
        this.setDescription(data.description());
        this.setStatus(data.status());
        this.setXp(data.xp());
        this.setCoins(data.coins());
        this.setType(data.type());
        this.setRepetition(data.repetition());
        this.setReminder(data.reminder());
        this.setSkillIncrease(data.skillIncrease());
        this.setSkillDecrease(data.skillDecrease());
        this.setLikes(data.likes());
    }
}
