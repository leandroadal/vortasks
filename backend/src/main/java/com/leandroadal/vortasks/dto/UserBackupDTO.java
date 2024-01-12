package com.leandroadal.vortasks.dto;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Achievements;
import com.leandroadal.vortasks.entities.backup.userprogress.CheckInDays;
import com.leandroadal.vortasks.entities.backup.userprogress.Goals;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.backup.userprogress.Task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBackupDTO {

    private String username;

    private int level;
    private float xp;
    private CheckInDays checkInDays;
    private Goals goals;
    private String lastModified;
    private List<Achievements> achievements;
    private List<Task> tasks;
    private List<Mission> missions;
    private List<Skill> skills;
}
