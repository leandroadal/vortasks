package com.leandroadal.vortasks.controllers.social;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.social.dto.FriendDTO;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.user.UserRepository;
import com.leandroadal.vortasks.services.social.FriendService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/social")
public class FriendController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

    @GetMapping(value = "/friends/{userId}")
    public ResponseEntity<List<FriendDTO>> getFriends(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(friendService.getFriendListDTOs(user));
    }

    @PostMapping(value = "/addFriends/{userId}")
    public ResponseEntity<String> addFriends(@PathVariable @NotNull @Positive Long userId,
            @Valid @RequestBody FriendDTO friendDTO) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A conta com o ID especificado não foi encontrada no sistema.");
        }
        User user = optionalUser.get();

        Friend friend = friendService.addFriend(friendDTO, user);

        if (friend != null) {
            return ResponseEntity.ok("amigo criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar o backup");
        }
    }

}
