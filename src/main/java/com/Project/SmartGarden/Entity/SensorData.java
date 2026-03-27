package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SensorData")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "data_id")
    private UUID id;
    @Column(name = "device_id")
    private UUID deviceId;
    @Column(name = "value")
    private double value;
    @Column(name = "type")
    private Type type;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
