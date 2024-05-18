package com.leandroadal.vortasks.controllers.social;

import java.util.List;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.controllers.social.doc.FriendshipDocSwagger;
import com.leandroadal.vortasks.entities.social.friend.Friendship;
import com.leandroadal.vortasks.entities.social.friend.dto.FriendshipDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.social.friendship.FriendshipService;

@RestController
@RequestMapping(value = "/social/friends")
public class FriendshipController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipService friendService;

    @GetMapping(value = "/{userId}")
    @FriendshipDocSwagger.GetFriendsSwagger
    public ResponseEntity<List<FriendshipDTO>> getFriends(@PathVariable @UUID String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(userId));

        List<Friendship> friends = friendService.getFriendshipsByUser(user);

        return ResponseEntity.ok(friends.stream().map(FriendshipDTO::new).toList());
    }

    @DeleteMapping("/{friendshipId}")
    @FriendshipDocSwagger.DeleteFriendshipSwagger
    public ResponseEntity<String> deleteFriendship(@PathVariable @UUID String friendshipId) {
        friendService.deleteFriendship(friendshipId);
        return ResponseEntity.noContent()
                .header("message", "Friendship deleted")
                .build();
    }

}
