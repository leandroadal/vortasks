package com.leandroadal.vortasks.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserProgressData;
import com.leandroadal.vortasks.entities.user.dto.UserCreateDTO;
import com.leandroadal.vortasks.repositories.user.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserCreateDTO userDTO) {
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
    public ResponseEntity<String> register(@RequestBody UserCreateDTO userDTO) {
        User newUser = new User(userDTO);

        UserProgressData newProgressData = new UserProgressData(0, 0, 1, 0.0f, newUser);
        newUser.setProgressData(newProgressData);

        userRepository.save(newUser);
        return ResponseEntity.ok("Requisição bem-sucedida!");
    }

    @GetMapping("/getAccounts")
    public List<User> getAllAccounts() {
        return userRepository.findAll();
    }

}
