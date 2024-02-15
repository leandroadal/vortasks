package com.leandroadal.vortasks.controllers.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;
import com.leandroadal.vortasks.entities.social.friend.Friendship;
import com.leandroadal.vortasks.entities.social.friend.dto.FriendInviteResponseDTO;
import com.leandroadal.vortasks.entities.social.friend.dto.FriendshipDTO;
import com.leandroadal.vortasks.entities.social.friend.dto.create.FriendInviteRequestDTO;
import com.leandroadal.vortasks.services.social.friendship.FriendInviteService;

@RestController
@RequestMapping(value = "/social/friends")
public class FriendInviteController {

    @Autowired
    private FriendInviteService service;

    @PostMapping("/request/{friendUserId}")
    public ResponseEntity<FriendInviteResponseDTO> sendFriendRequest(@PathVariable String friendUserId, @RequestBody FriendInviteRequestDTO request) {
        FriendInvite friendInvite = service.sendFriendRequest(request.toFriendInvite(friendUserId));

        return ResponseEntity.ok(new FriendInviteResponseDTO(friendInvite));
    }

    @PostMapping("/accept/{friendRequestId}")
    public ResponseEntity<FriendshipDTO> acceptFriendRequest(@PathVariable String friendRequestId, @RequestBody FriendInviteRequestDTO request) {
        Friendship friendInvite = service.acceptFriendRequest(request.userId(), friendRequestId);

        return ResponseEntity.ok(new FriendshipDTO(friendInvite));
    }

    @PutMapping("/refuse/{friendRequestId}")
    public ResponseEntity<String> refusedFriendInvite(@PathVariable String friendRequestId, @RequestBody FriendInviteRequestDTO request) {
        service.refusedFriendInvite(request.userId(), friendRequestId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cancel/{friendRequestId}")
    public ResponseEntity<String> cancelFriendInvite(@PathVariable String friendRequestId, @RequestBody FriendInviteRequestDTO request) {
        service.cancelFriendInvite(friendRequestId);
        return ResponseEntity.noContent().build();
    }

}
