package com.leandroadal.vortasks.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserRole;
import com.leandroadal.vortasks.entities.user.dto.LoginDTO;
import com.leandroadal.vortasks.entities.user.dto.UserResponseDTO;
import com.leandroadal.vortasks.entities.user.dto.create.UserCreateDTO;
import com.leandroadal.vortasks.entities.user.dto.create.UserCreateResponse;
import com.leandroadal.vortasks.entities.user.ProgressData;
import com.leandroadal.vortasks.repositories.user.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.username()).orElseThrow();
        if (user.getPassword().equals(userDTO.password())) {
            return ResponseEntity.ok("Requisição bem-sucedida!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Requisição bem-sucedida!");
    }

    @PostMapping("/register")
    public ResponseEntity<UserCreateResponse> register(@RequestBody UserCreateDTO userDTO) {
        User newUser = userDTO.toUser();
        newUser.setRole(UserRole.USER);
        ProgressData newProgressData = new ProgressData(null, 0, 0, 1, 0.0f, newUser);
        newUser.setProgressData(newProgressData);

        userRepository.save(newUser);
        return ResponseEntity.ok(new UserCreateResponse(newUser));
    }

    @GetMapping("/getAccounts")
    public List<UserResponseDTO> getAllAccounts() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }

}
