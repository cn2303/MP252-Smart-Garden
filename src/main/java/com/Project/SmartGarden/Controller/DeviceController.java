package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.DeviceRequest;
import com.Project.SmartGarden.DTO.Respone.DeviceResponde;
import com.Project.SmartGarden.Entity.Type;
import com.Project.SmartGarden.Service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/device")
public class DeviceController {
    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable UUID id) {
        DeviceResponde device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }
    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody DeviceRequest request) {
        DeviceResponde deviceResponde = this.deviceService.save(request);
        return ResponseEntity.ok(deviceResponde);
    }
    @GetMapping("/pump/{id}")
    public ResponseEntity<?> getDeviceByPumpId(@PathVariable UUID id) {
        List<DeviceResponde> deviceResponses = deviceService.getDeviceByPumpId(id);
        return ResponseEntity.ok(deviceResponses);
    }
    @GetMapping("/pump/{id}/{type}")
    public ResponseEntity<?> getDeviceByPumpIdAndType(@PathVariable UUID id, @PathVariable Type type) {
        DeviceResponde deviceResponses = deviceService.getDeviceByPumpIdAndType(id, type);
        return ResponseEntity.ok(deviceResponses);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable UUID id) {
        deviceService.deleteDeviceById(id);
        return ResponseEntity.ok().build();
    }
}
