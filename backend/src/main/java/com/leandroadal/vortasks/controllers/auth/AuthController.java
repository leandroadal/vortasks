package com.leandroadal.vortasks.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.dto.LoginDTO;
import com.leandroadal.vortasks.entities.user.dto.LoginResponseDTO;
import com.leandroadal.vortasks.entities.user.dto.create.UserCreateDTO;
import com.leandroadal.vortasks.entities.user.dto.create.UserCreateResponse;
import com.leandroadal.vortasks.services.auth.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO data) { 
        String token = service.login(data.username(), data.password());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        service.logout(token);
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }

    @PostMapping("/register")
    public ResponseEntity<UserCreateResponse> register(@RequestBody UserCreateDTO userDTO) {
        User newUser = userDTO.toUser();
        newUser = service.register(newUser);
        return ResponseEntity.ok(new UserCreateResponse(newUser));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/admin")
    public ResponseEntity<UserCreateResponse> createAdmin(@RequestBody UserCreateDTO userDTO) {
        User newUser = userDTO.toUser();
        newUser = service.createAdmin(newUser);
        return ResponseEntity.ok(new UserCreateResponse(newUser));
    }

}
