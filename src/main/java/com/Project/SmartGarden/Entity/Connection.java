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
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conn_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "broker_name")
    private String brokerName;
    @Column(name = "feed")
    private String feed;
    @Column(name = "password")
    private String password;
    @Column(name = "addr_broker")
    private String addrBroker;
}
