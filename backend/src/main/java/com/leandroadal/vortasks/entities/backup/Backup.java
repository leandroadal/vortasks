package com.leandroadal.vortasks.entities.backup;

import java.time.Instant;
import java.util.ArrayList;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Backup {

    public Backup(Instant lastModified) {
        this.lastModified = lastModified;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Instant lastModified;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CheckInDays checkInDays;

    @OneToOne(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Goals goals; // metas

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "userBackup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();
    
}
