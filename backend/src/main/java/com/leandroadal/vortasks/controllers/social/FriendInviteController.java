package com.leandroadal.vortasks.controllers.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/social/friends/invite")
public class FriendInviteController {

    @Autowired
    private FriendInviteService service;

    @GetMapping("{userId}")
    public ResponseEntity<List<FriendInviteResponseDTO>> getMyFriendInvite(@PathVariable String userId) {
        List<FriendInvite> friendship = service.friendInvitesForUser(userId);
        return ResponseEntity.ok(friendship.stream().map(FriendInviteResponseDTO::new).toList());
    }

    @GetMapping
    public ResponseEntity<List<FriendInviteResponseDTO>> getFriendInvites() {
        List<FriendInvite> friendship = service.findAll();
        return ResponseEntity.ok(friendship.stream().map(FriendInviteResponseDTO::new).toList());
    }

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<FriendInviteResponseDTO> getFriendInvite(@PathVariable String senderId, @PathVariable String receiverId) {
        FriendInvite friendInvite = service.findFriendInvite(senderId, receiverId);
        return ResponseEntity.ok(new FriendInviteResponseDTO(friendInvite));
    }

    @PostMapping("/{senderId}/{receiverId}")
    public ResponseEntity<FriendInviteResponseDTO> sendFriendRequest(@PathVariable String senderId, @PathVariable String receiverId) {
        FriendInvite friendInvite = service.sendFriendRequest(senderId, receiverId);

        return ResponseEntity.ok(new FriendInviteResponseDTO(friendInvite));
    }

    @PostMapping("/accept/{senderId}/{receiverId}")
    public ResponseEntity<FriendshipDTO> acceptFriendRequest(@PathVariable String senderId, @PathVariable String receiverId) {
        Friendship friendInvite = service.acceptFriendRequest(senderId, receiverId);

        return ResponseEntity.ok(new FriendshipDTO(friendInvite));
    }

    @PutMapping("/refuse/{senderId}/{receiverId}")
    public ResponseEntity<String> refusedFriendInvite(@PathVariable String senderId, @PathVariable String receiverId, @RequestBody FriendInviteRequestDTO request) {
        service.refusedFriendInvite(senderId, receiverId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cancel/{senderId}/{receiverId}")
    public ResponseEntity<String> cancelFriendInvite(@PathVariable String senderId, @PathVariable String receiverId, @RequestBody FriendInviteRequestDTO request) {
        service.cancelFriendInvite(senderId, receiverId);
        return ResponseEntity.noContent().build();
    }

}
