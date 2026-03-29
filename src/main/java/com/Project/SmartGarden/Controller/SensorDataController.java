package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.Entity.SensorData;
import com.Project.SmartGarden.Service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/sensor/data")
public class SensorDataController {
    private final SensorDataService sensorDataService;
    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }
    @GetMapping("/{deviceId}")
    public ResponseEntity<?> findSensorDataByDeviceId(@PathVariable("deviceId") UUID deviceId) {
        List<SensorData> sensorDataList = this.sensorDataService.findSensorDataByDeviceId(deviceId);
        return ResponseEntity.ok(sensorDataList);
    }
    @GetMapping
    public ResponseEntity<?> findSensorDataByDeviceIdAndCreatedAtBetween(@RequestBody UUID id, @RequestBody LocalDateTime start, @RequestBody LocalDateTime end) {
        List<SensorData> sensorDataList = this.sensorDataService.findSensorDataByDeviceIdAndCreatedAtBetween(id, start, end);
        return ResponseEntity.ok(sensorDataList);
    }
}
