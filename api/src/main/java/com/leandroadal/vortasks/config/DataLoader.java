package com.leandroadal.vortasks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.leandroadal.vortasks.entities.user.ProgressData;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.entities.user.UserRole;
import com.leandroadal.vortasks.repositories.user.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.initial-load}")
    private boolean initialLoad;

    @Override
    public void run(String... args) throws Exception {
        if (initialLoad) {
            User user = new User("admin", "admin", "admin@gmail.com", "admin");
            encryptedPassword(user);
            progressData(100000000, 100000000, 90, 25200f, user);
            userRepository.save(user);

            user = new User("user", "user", "user@gmail.com", "user");
            encryptedPassword(user);
            progressData(2548, 1549203, 10, 20, user);
            userRepository.save(user);

            initialLoad = false;
        }
        
    }

    private void encryptedPassword(User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(UserRole.ADMIN);
    }

    private void progressData(int coins, int gems, int level, float xp, User user) {
        ProgressData progressData = new ProgressData(null, coins, gems, level, 25200f, null, user);
        user.setProgressData(progressData);
    }

}
