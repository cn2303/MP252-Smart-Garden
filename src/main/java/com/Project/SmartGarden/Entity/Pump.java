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

    //Pump-Config
    @Column(name = "temperature_max")
    private double temperatureMax;
    @Column(name = "temperature_min")
    private double temperatureMin;
    @Column(name = "light_intensity_max")
    private double lightIntensityMax;
    @Column(name = "moisture_threshold")
    private double moistureThreshold;
    @Column(name = "field_capacity")
    private double fieldCapacity;
    @Column(name = "root_depth")
    private double rootDepth;
    @Column(name = "area")
    private double area;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
