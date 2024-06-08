package com.leandroadal.vortasks.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.controllers.user.doc.UserSwagger;
import com.leandroadal.vortasks.entities.user.dto.UserResponseDTO;
import com.leandroadal.vortasks.services.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserResponseDTO> findUser() {
        return ResponseEntity.ok(new UserResponseDTO(service.findUser()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    @UserSwagger.FindAllUsersSwagger
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAllUsers().stream().map(UserResponseDTO::new).toList());
    }

}
