package com.leandroadal.vortasks.entities.backup;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Achievement;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserBackup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // Referência para a conta associada

    private int level;
    private float xp;
    private String lastModified;

    @OneToOne(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CheckInDays checkInDays;

    @OneToOne(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Goals goals; // metas

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mission> missions;

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills;
}
