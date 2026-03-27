package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.PumpRequest;
import com.Project.SmartGarden.DTO.Respone.PumpResponse;
import com.Project.SmartGarden.Service.PumpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/pump")
public class PumpController {
    private final PumpService pumpService;
    public PumpController(PumpService pumpService) {
        this.pumpService = pumpService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPumpById(@PathVariable("id") UUID id) {
        PumpResponse pumpResponse = this.pumpService.getByPumpId(id);
        return ResponseEntity.ok(pumpResponse);
    }
    @GetMapping("/user/{userid}")
    public ResponseEntity<?> getPumpByUserId(@PathVariable("userid") UUID userid) {
        List<PumpResponse> pumpResponseList = this.pumpService.getPumpByUserId(userid);
        return ResponseEntity.ok(pumpResponseList);
    }
    @PostMapping
    public ResponseEntity<?> createPump(@RequestBody PumpRequest pumpRequest) {
        PumpResponse pumpResponse = this.pumpService.save(pumpRequest);
        return ResponseEntity.ok(pumpResponse);
    }
    @PutMapping
    public ResponseEntity<?> updatePump(@RequestBody PumpRequest pumpRequest) {
        PumpResponse pumpResponse = this.pumpService.save(pumpRequest);
        return ResponseEntity.ok(pumpResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePump(@PathVariable("id") UUID id) {
        this.pumpService.delete(id);
        return ResponseEntity.ok().build();
    }
}
