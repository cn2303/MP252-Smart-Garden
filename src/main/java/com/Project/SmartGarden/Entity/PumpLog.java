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
@Table(name = "pump_logs")
public class PumpLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer id;
    @Column(name = "pump_id")
    private Integer pumpId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "action", columnDefinition = "pump_state_enum")
    @Enumerated(EnumType.STRING)
    private PumpStatus action;
    @Column(name = "mode" ,columnDefinition = "pump_mode_enum")
    @Enumerated(EnumType.STRING)
    private Mode mode;
    @Column(name = "water_volume")
    private double waterVolume;
    @Column(name = "status", columnDefinition = "pump_log_status_enum")
    @Enumerated(EnumType.STRING)
    private ActionStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
