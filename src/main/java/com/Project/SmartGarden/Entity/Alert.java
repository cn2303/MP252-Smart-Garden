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
@Table(name = "Alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "alert_id")
    private UUID id;
    @Column(name = "device_id")
    private UUID deviceId;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "value")
    private double value;
    @Column(name = "threshold")
    private double threshold;
    @Column(name = "message")
    private String message;
    @Column(name = "is_read")
    private boolean isRead;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
