package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
