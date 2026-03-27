package com.Project.SmartGarden.Mapper;

import com.Project.SmartGarden.DTO.Request.DeviceRequest;
import com.Project.SmartGarden.DTO.Respone.DeviceResponde;
import com.Project.SmartGarden.Entity.Device;
import com.Project.SmartGarden.Entity.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeviceMapper {
    public DeviceResponde toDTO(Device device) {
        if (device == null) {
            return null;
        }
        DeviceResponde deviceResponde = new DeviceResponde();
        deviceResponde.setId(device.getId());
        deviceResponde.setName(device.getName());
        deviceResponde.setConnectionId(device.getConnectId());
        deviceResponde.setPumpId(device.getPumpId());
        deviceResponde.setStatus(device.getStatus());
        deviceResponde.setType(device.getType());
        deviceResponde.setLastSeen(device.getLastSeen());
        return deviceResponde;
    }
    public Device toEntity(DeviceRequest request) {
        Device device = new Device();
        device.setName(request.getName());
        device.setConnectId(request.getConnectId());
        device.setPumpId(request.getPumpId());
        device.setType(request.getType());
        device.setStatus(Status.ONLINE);
        device.setLastSeen(LocalDateTime.now());
        return device;
    }
}
