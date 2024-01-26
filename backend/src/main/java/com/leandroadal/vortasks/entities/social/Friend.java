package com.leandroadal.vortasks.entities.social;

import com.leandroadal.vortasks.dto.social.FriendDTO;
import com.leandroadal.vortasks.entities.user.UserProgressData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Friend {

    public Friend(FriendDTO data) {
        this.username = data.username();
        this.level = data.level();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

	private String username;
    private int level;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProgressData progressData;

    public void edit(FriendDTO data) {
        this.username = data.username();
        this.level = data.level();
    }
}
