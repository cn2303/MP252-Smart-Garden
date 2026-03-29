package com.Project.SmartGarden.Configuration;

import com.Project.SmartGarden.Entity.User;
import com.Project.SmartGarden.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ApplicationConfig {
    private final UserRepository userRepository;
    @Autowired
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void init() {
        User user = this.userRepository.findByUsername("admin").orElse(null);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (user == null) {
            User newUser = new User();
            newUser.setUsername("admin");
            newUser.setPassword(passwordEncoder.encode("admin"));
            newUser.setEmail("admin@gmail.com");
            newUser.setFullName("Admin");
            newUser.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(newUser);
        }
    }
}
