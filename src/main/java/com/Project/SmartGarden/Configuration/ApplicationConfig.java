package com.Project.SmartGarden.Configuration;

import com.Project.SmartGarden.Entity.Connection;
import com.Project.SmartGarden.Entity.User;
import com.Project.SmartGarden.Repository.ConnectionRepository;
import com.Project.SmartGarden.Repository.UserRepository;
import com.Project.SmartGarden.Service.MqttConnectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            ConnectionRepository connectionRepository,
            MqttConnectionService mqttConnectionService,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // Initialize admin user
            User user = userRepository.findByUsername("admin").orElse(null);

            if (user == null) {
                User newUser = new User();
                newUser.setUsername("admin");
                newUser.setPassword(passwordEncoder.encode("admin"));
                newUser.setEmail("admin@gmail.com");
                newUser.setFullName("Admin");
                newUser.setCreatedAt(LocalDateTime.now());

                userRepository.save(newUser);
            }

            // Initialize MQTT connections
            List<Connection> connections = connectionRepository.findAll();

            for (Connection connection : connections) {
                mqttConnectionService.connect(connection);
            }
        };
    }
}