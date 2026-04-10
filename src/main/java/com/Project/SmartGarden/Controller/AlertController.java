package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.Entity.Alert;
import com.Project.SmartGarden.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> getAlert(@PathVariable Integer id) {
        Alert alert = this.alertService.getById(id);
        return ResponseEntity.ok(alert);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAlertByUserId(@PathVariable Integer userId) {
        List<Alert> alerts = this.alertService.getByUserId(userId);
        return ResponseEntity.ok(alerts);
    }
    @PutMapping("/isRead/{id}")
    public ResponseEntity<?> changeIsRead(@PathVariable Integer id) {
        Alert alert = this.alertService.changeIsRead(id);
        return ResponseEntity.ok(alert);
    }
}
