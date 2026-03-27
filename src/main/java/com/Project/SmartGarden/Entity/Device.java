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
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "device_id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
    @Column(name = "connect_id")
    private UUID connectId;
    @Column(name = "pump_id")
    private UUID pumpId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "last_seen")
    private LocalDateTime lastSeen;
}
