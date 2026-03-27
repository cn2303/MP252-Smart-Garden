package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "phone_id")
    private UUID id;
    @Column(name = "phone_name")
    private String name;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "token")
    private String token;
}
