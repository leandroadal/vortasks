package com.leandroadal.vortasks.entities.backup.userprogress;

import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mission extends AbstractMission {

    public Mission(MissionDTO data, UserBackup userBackup) {
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
        this.userBackup = userBackup;
    }

    @ManyToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Task> requirements; // tarefas necessárias para concluir a missão
 
    public void edit(MissionDTO missionDTO) {
        this.setTitle(missionDTO.title());
        this.setDescription(missionDTO.description());
        this.setStatus(missionDTO.status());
        this.setXp(missionDTO.xp());
        this.setCoins(missionDTO.coins());
        this.setType(missionDTO.type());
        this.setRepetition(missionDTO.repetition());
        this.setReminder(missionDTO.reminder());
        this.setSkillIncrease(missionDTO.skillIncrease());
        this.setSkillDecrease(missionDTO.skillDecrease());
    }

    public Mission(MissionDTO missionDTO, UserBackup userBackup2, List<TaskDTO> collect) {
        //TODO Auto-generated constructor stub
    }
}
