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
    private Integer id;
    private String name;
    private Type type;
    private Integer connectionId;
    private Integer pumpId;
    private Status status;
    private LocalDateTime lastSeen;
}
