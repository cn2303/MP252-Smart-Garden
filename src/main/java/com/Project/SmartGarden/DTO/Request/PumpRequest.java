package com.Project.SmartGarden.DTO.Request;

import com.Project.SmartGarden.Entity.PumpStatus;
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
public class PumpRequest {
    private String name;
    private Integer connectionId;
    private Integer userId;
}
