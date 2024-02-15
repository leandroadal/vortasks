package com.leandroadal.vortasks.entities.social.friend;

import java.time.Instant;
import com.leandroadal.vortasks.entities.social.friend.enums.FriendStatus;
import com.leandroadal.vortasks.entities.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FriendInvite {

    @Id // TODO muda para @EmbeddedId
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User senderUser;

    @ManyToOne
    private User receiverUser;

    private Instant requestDate;

    @Enumerated(EnumType.STRING)
    private FriendStatus status;


}
