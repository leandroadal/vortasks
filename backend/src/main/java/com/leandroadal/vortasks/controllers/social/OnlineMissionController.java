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

import com.leandroadal.vortasks.dto.social.OnlineMissionDTO;
import com.leandroadal.vortasks.entities.social.OnlineMission;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.UserRepository;
import com.leandroadal.vortasks.services.social.OnlineMissionService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/social")
public class OnlineMissionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OnlineMissionService onlineMissionService;

    @GetMapping(value = "/onlineMissions/{userId}")
    public ResponseEntity<List<OnlineMissionDTO>> onlineMissions(@PathVariable @Positive Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(onlineMissionService.getOnlineMissionListDTOs(optionalUser));
    }

    @PostMapping(value = "/addOnlineMissions/{userId}")
    public ResponseEntity<String> addOnlineMissions(@PathVariable @Positive Long userId,
            @RequestBody OnlineMissionDTO onlineMissionDTO) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A conta com o ID especificado não foi encontrada no sistema.");
        }

        User account = optionalUser.get();

        OnlineMission onlineMission = onlineMissionService.addOnlineMission(onlineMissionDTO, account);

        if (onlineMission != null) {
            return ResponseEntity.ok("Missão online criada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar a missão");
        }
    }
}
