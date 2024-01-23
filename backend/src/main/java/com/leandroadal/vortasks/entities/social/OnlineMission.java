package com.leandroadal.vortasks.entities.social;

import com.leandroadal.vortasks.dto.userprogress.MissionDTO;
import com.leandroadal.vortasks.entities.backup.userprogress.Mission;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OnlineMission extends Mission {

    public OnlineMission(MissionDTO data, int likes) {
        super(data);
        this.likes = likes;
    }

    private int likes;
    
    @ManyToOne
    private User user;
}
