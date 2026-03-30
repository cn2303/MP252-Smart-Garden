package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.Entity.Phone;
import com.Project.SmartGarden.Service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/phone")
public class PhoneController {
    private final PhoneService phoneService;
    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }
    @PostMapping
    public ResponseEntity<?> addPhone(@RequestBody Phone phone) {
        Phone phone1 = this.phoneService.save(phone);
        return ResponseEntity.ok(phone1);
    }
    @PutMapping
    public ResponseEntity<?> updatePhone(@RequestBody Phone phone) {
        Phone phone1 = this.phoneService.update(phone);
        return ResponseEntity.ok(phone1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable Integer id) {
        this.phoneService.delete(id);
        return ResponseEntity.ok().build();
    }
}
