package com.leandroadal.vortasks.entities.backup.userprogress;

import com.leandroadal.vortasks.dto.userprogress.TaskDTO;
import com.leandroadal.vortasks.entities.backup.UserBackup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task {

    public Task(TaskDTO data) {
        this.status = data.status();
        this.name = data.name();
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
    
    @ManyToOne(optional = true)
    private Mission mission; // Pode ou não estar associada a uma missão
    
    private String status; // Concluído, falha ou em andamento
    private String name;
    private String description;
    private int xp;
    private float coins; // moeda
    private String type; // Lazer ou Atividade
    private int repetition; // Diária(1), semanal(7) ou mensal(30)
    private String reminder;
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease;

}
