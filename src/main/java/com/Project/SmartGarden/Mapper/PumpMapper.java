package com.Project.SmartGarden.Mapper;

import com.Project.SmartGarden.DTO.Request.PumpRequest;
import com.Project.SmartGarden.DTO.Respone.PumpResponse;
import com.Project.SmartGarden.Entity.Pump;
import com.Project.SmartGarden.Entity.PumpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PumpMapper {
    public PumpResponse toDTO(Pump pump) {
        if (pump == null) {
            return null;
        }
        PumpResponse pumpResponse = new PumpResponse();
        pumpResponse.setPumpId(pump.getId());
        pumpResponse.setPumpStatus(pump.getStatus());
        pumpResponse.setName(pump.getName());
        pumpResponse.setConnectionId(pump.getConnectionId());
        pumpResponse.setUserId(pump.getUserId());
        pumpResponse.setUpdateAt(pump.getUpdatedAt());
        return pumpResponse;
    }
    public Pump toEntity(PumpRequest request) {
        Pump pump = new Pump();
        pump.setName(request.getName());
        pump.setConnectionId(request.getConnectionId());
        pump.setUserId(request.getUserId());
        pump.setStatus(PumpStatus.OFF);

        pump.setTemperatureMax(request.getTemperatureMax());
        pump.setTemperatureMin(request.getTemperatureMin());
        pump.setLightIntensityMax(request.getLightIntensityMax());
        pump.setMoistureThreshold(request.getMoistureThreshold());
        pump.setFieldCapacity(request.getFieldCapacity());
        pump.setRootDepth(request.getRootDepth());
        pump.setArea(request.getArea());
        pump.setUpdatedAt(LocalDateTime.now());
        return pump;
    }
}
