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
@Table(name = "Configuration")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "config_id")
    private UUID id;
    @Column(name = "pump_id")
    private UUID pumpId;
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
