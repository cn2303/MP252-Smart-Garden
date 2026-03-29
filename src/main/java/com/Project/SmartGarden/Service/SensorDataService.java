package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.SensorData;
import com.Project.SmartGarden.Repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SensorDataService {
    private final SensorDataRepository sensorDataRepository;
    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }
    public SensorData saveSensorData(SensorData sensorData) {
        return this.sensorDataRepository.save(sensorData);
    }
    public List<SensorData> findSensorDataByDeviceId(UUID deviceId) {
        return this.sensorDataRepository.findByDeviceId(deviceId);
    }
    public List<SensorData> findSensorDataByDeviceIdAndCreatedAtBetween(UUID deviceId, LocalDateTime start, LocalDateTime end) {
        return this.sensorDataRepository.findByDeviceIdAndCreatedAtBetween(deviceId, start, end);
    }
}
