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
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer id;
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "type", columnDefinition = "measurement_type_enum")
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
