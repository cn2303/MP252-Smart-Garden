package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Connection")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "broker_name")
    private String brokerName;
    @Column(name = "feed")
    private String feed;
    @Column(name = "password")
    private String password;
    @Column(name = "addr_broker")
    private String addrBroker;
}
