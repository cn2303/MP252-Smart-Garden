package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.Entity.PumpLog;
import com.Project.SmartGarden.Service.PumpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/pumpLog")
public class PumpLogController {
    private final PumpLogService pumpLogService;
    @Autowired
    public PumpLogController(PumpLogService pumpLogService) {
        this.pumpLogService = pumpLogService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPumpLogById(@PathVariable Integer id) {
        PumpLog pumpLog = this.pumpLogService.getById(id);
        return ResponseEntity.ok(pumpLog);
    }
    @GetMapping("/pump/{pumpId}")
    public ResponseEntity<?> getPumpLogByPumpId(@PathVariable Integer pumpId) {
        List<PumpLog> pumpLogs = this.pumpLogService.getByPumpId(pumpId);
        return ResponseEntity.ok(pumpLogs);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPumpLogByUserId(@PathVariable Integer userId) {
        List<PumpLog> pumpLogs = this.pumpLogService.getByUserId(userId);
        return ResponseEntity.ok(pumpLogs);
    }
}
