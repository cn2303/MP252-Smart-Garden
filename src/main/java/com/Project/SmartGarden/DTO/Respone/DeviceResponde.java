package com.Project.SmartGarden.DTO.Respone;

import com.Project.SmartGarden.Entity.Status;
import com.Project.SmartGarden.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponde {
    private UUID id;
    private String name;
    private Type type;
    private UUID connectionId;
    private UUID pumpId;
    private Status status;
    private LocalDateTime lastSeen;
}
