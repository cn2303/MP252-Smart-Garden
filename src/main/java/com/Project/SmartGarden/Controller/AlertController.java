package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.Entity.Alert;
import com.Project.SmartGarden.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping
public class AlertController {
    private final AlertService alertService;
    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlert(@PathVariable UUID id) {
        Alert alert = this.alertService.getById(id);
        return ResponseEntity.ok(alert);
    }
    @PutMapping("/isRead/{id}")
    public ResponseEntity<?> changeIsRead(@PathVariable UUID id) {
        Alert alert = this.alertService.changeIsRead(id);
        return ResponseEntity.ok(alert);
    }
}
