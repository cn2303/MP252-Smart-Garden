package com.Project.SmartGarden.DTO.Request;

import com.Project.SmartGarden.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequest {
    private String name;
    private Type type;
    private UUID connectId;
    private UUID pumpId;
}
