package com.leandroadal.vortasks.repositories.social;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;

public interface FriendInviteRepository extends JpaRepository<FriendInvite, String> {
    //List<FriendInvite> findByFriendUserAndStatus(User user, FriendStatus status);
}
