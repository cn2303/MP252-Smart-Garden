package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.DeviceRequest;
import com.Project.SmartGarden.DTO.Respone.DeviceResponde;
import com.Project.SmartGarden.Entity.Type;
import com.Project.SmartGarden.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/device")
public class DeviceController {
    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GetMapping
    public ResponseEntity<?>  getDevices() {
        List<DeviceResponde> devices = deviceService.getDevices();
        return ResponseEntity.ok(devices);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Integer id) {
        DeviceResponde device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }
    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody DeviceRequest request) {
        DeviceResponde deviceResponde = this.deviceService.save(request);
        return ResponseEntity.ok(deviceResponde);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Integer id,@RequestBody DeviceRequest request) {
        DeviceResponde deviceResponde = this.deviceService.update(id, request);
        return ResponseEntity.ok(deviceResponde);
    }
    @GetMapping("/by-pump")
    public ResponseEntity<?> getDeviceByPumpId(@RequestParam Integer pumpId) {
        List<DeviceResponde> deviceResponses = deviceService.getDeviceByPumpId(pumpId);
        return ResponseEntity.ok(deviceResponses);
    }
    @GetMapping("/by-pump-and-type")
    public ResponseEntity<?> getDeviceByPumpIdAndType(@RequestParam Integer pumpId, @RequestParam Type type) {
        DeviceResponde deviceResponses = deviceService.getDeviceByPumpIdAndType(pumpId, type);
        return ResponseEntity.ok(deviceResponses);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable Integer id) {
        deviceService.deleteDeviceById(id);
        return ResponseEntity.ok().build();
    }
}
