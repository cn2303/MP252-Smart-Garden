package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pump")
public class Pump {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pump_id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "connection_id")
    private UUID connectionId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PumpStatus status;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
