package com.Project.SmartGarden.DTO.Respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionResponse {
    private UUID connectionId;
    private UUID userId;
    private String broker_name;
    private String feed;
    private String addr;
}
