package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PumpLog")
public class PumpLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "log_id")
    private UUID id;
    @Column(name = "pump_id")
    private UUID pumpId;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private PumpStatus action;
    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private Mode mode;
    @Column(name = "water_volume")
    private double waterVolume;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActionStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
