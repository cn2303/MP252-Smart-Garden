package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.DeviceRequest;
import com.Project.SmartGarden.DTO.Respone.DeviceResponde;
import com.Project.SmartGarden.Entity.Device;
import com.Project.SmartGarden.Entity.Type;
import com.Project.SmartGarden.Mapper.DeviceMapper;
import com.Project.SmartGarden.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = new DeviceMapper();
    }
    public List<DeviceResponde> getDevices() {
        List<DeviceResponde> devices = new ArrayList<>();
        for (Device device : deviceRepository.findAll()) {
            devices.add(this.deviceMapper.toDTO(device));
        }
        return devices;
    }
    public DeviceResponde save(DeviceRequest request) {
        Device  device = deviceMapper.toEntity(request);
        Device returnDevice = this.deviceRepository.save(device);
        return deviceMapper.toDTO(returnDevice);
    }
    public DeviceResponde update(Integer id,DeviceRequest request) {
        Device device = this.deviceRepository.findById(id).orElse(null);
        device.setConnectId(request.getConnectId());
        Device returnDevice = this.deviceRepository.save(device);
        return deviceMapper.toDTO(returnDevice);
    }
    public DeviceResponde getDeviceById(Integer id) {
        Device device = this.deviceRepository.findById(id).orElse(null);
        return deviceMapper.toDTO(device);
    }
    public List<DeviceResponde> getDeviceByPumpId(Integer pumpId) {
        List<Device> devices = deviceRepository.findByPumpId(pumpId);
        List<DeviceResponde> deviceRespondes = new ArrayList<>();
        for (Device device : devices) {
            deviceRespondes.add(deviceMapper.toDTO(device));
        }
        return deviceRespondes;
    }
    public DeviceResponde getDeviceByPumpIdAndType(Integer pumpId, Type type) {
        Device device =  this.deviceRepository.findByPumpIdAndType(pumpId, type);
        return deviceMapper.toDTO(device);

    }
    public void deleteDeviceById(Integer id) {
        deviceRepository.deleteById(id);
    }
//    public DeviceResponde updateLastSeen(Integer id) {
//        Device device = this.deviceRepository.findById(id).orElse(null);
//        device.setLastSeen(LocalDateTime.now());
//        deviceRepository.save(device);
//        return deviceMapper.toDTO(device);
//    }
}
