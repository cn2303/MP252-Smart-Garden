package com.Project.SmartGarden.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionUpdateRequest {
    private String brokerName;
    private String feed;
    private String password;
    private String address;
}
