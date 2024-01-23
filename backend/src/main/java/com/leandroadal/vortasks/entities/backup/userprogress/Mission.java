package com.leandroadal.vortasks.entities.backup.userprogress;

import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mission {

    public Mission(MissionDTO data) {
        this.status = data.status();
        this.title = data.title();
        this.description = data.description();
        this.xp = data.xp();
        this.coins = data.coins();
        this.type = data.type();
        this.repetition = data.repetition();
        this.reminder = data.reminder();
        this.skillIncrease = data.skillIncrease();
        this.skillDecrease = data.skillDecrease();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_backup_id")
    private UserBackup userBackup;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Task> requirements; // tarefas necessárias para concluir a missão

    private String status; // Concluído, falha ou em andamento
    private String title;
    private String description;
    private int xp; // xp
    private float coins;
    private String type; // Lazer  ou atividade
    private String repetition;
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease; // diminuir xp da skill
 
}
