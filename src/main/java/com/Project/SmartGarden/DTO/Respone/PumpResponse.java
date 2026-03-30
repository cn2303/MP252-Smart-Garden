package com.Project.SmartGarden.DTO.Respone;

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
public class PumpResponse {
    private Integer pumpId;
    private String name;
    private PumpStatus pumpStatus;
    private Integer connectionId;
    private Integer userId;
    private LocalDateTime updateAt;
}
