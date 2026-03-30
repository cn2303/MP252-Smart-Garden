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
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer id;
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "value")
    private double value;
    @Column(name = "type", columnDefinition = "measurement_type_enum")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "unit")
    private String unit;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
