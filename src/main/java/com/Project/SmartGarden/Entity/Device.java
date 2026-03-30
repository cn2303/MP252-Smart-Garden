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
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "measurement_type_enum")
    private Type type;
    @Column(name = "conn_id")
    private Integer connectId;
    @Column(name = "pump_id")
    private Integer pumpId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "device_status_enum")
    private Status status;
    @Column(name = "last_seen")
    private LocalDateTime lastSeen;
}
