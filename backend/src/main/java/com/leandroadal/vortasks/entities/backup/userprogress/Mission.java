package com.leandroadal.vortasks.entities.backup.userprogress;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.Backup;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.AbstractMissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.dto.create.AbstractMissionCreateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mission extends AbstractMission {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup_id")
    private Backup userBackup;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionTasks> requirements; // tarefas necessárias para concluir a missão



    public Mission(@NotNull String title, String description, Status status, int xp, int coins, Type type,
            String repetition, String reminder, int skillIncrease, int skillDecrease, Backup userBackup,
            List<MissionTasks> requirements) {
        super(title, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.userBackup = userBackup;
        this.requirements = requirements;
    }

    public Mission(String id, String title, String description, Status status, int xp, int coins, Type type,
            String repetition, String reminder, int skillIncrease, int skillDecrease, Backup userBackup,
            List<MissionTasks> requirements) {
        super(id, title, description, status, xp, coins, type, repetition, reminder, skillIncrease, skillDecrease);
        this.userBackup = userBackup;
        this.requirements = requirements;
    }

    public Mission(AbstractMissionCreateDTO missionDTO, Backup userBackup) {
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
        this.userBackup = userBackup;
    }

    public void edit(AbstractMissionDTO missionDTO) {
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

}
