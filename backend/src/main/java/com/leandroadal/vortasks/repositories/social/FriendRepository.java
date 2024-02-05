package com.leandroadal.vortasks.repositories.social;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandroadal.vortasks.entities.social.Friend;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByProgressDataId(Long userId);
}
