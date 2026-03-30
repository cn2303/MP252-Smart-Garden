package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.ConnectionRequest;
import com.Project.SmartGarden.DTO.Request.ConnectionUpdateRequest;
import com.Project.SmartGarden.DTO.Respone.ConnectionResponse;
import com.Project.SmartGarden.Service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/connection")
public class ConnectionController {
    private final ConnectionService connectionService;
    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getConnectionById(@PathVariable("id") Integer id) {
        ConnectionResponse connectionResponse = this.connectionService.getConnectionById(id);
        return ResponseEntity.ok(connectionResponse);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getConnectionByUserId(@PathVariable("userId") Integer id) {
        List<ConnectionResponse> connectionResponse = this.connectionService.getConnectionByUserId(id);
        return ResponseEntity.ok(connectionResponse);
    }
    @PostMapping
    public ResponseEntity<?> createConnection(@RequestBody ConnectionRequest connectionRequest) {
        ConnectionResponse connectionResponse = this.connectionService.saveConnection(connectionRequest);
        return ResponseEntity.ok(connectionResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateConnection(@PathVariable Integer id, @RequestBody ConnectionUpdateRequest request) {
        ConnectionResponse connectionResponse = this.connectionService.updateConnection(id,request);
        return ResponseEntity.ok(connectionResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConnectionById(@PathVariable("id") Integer id) {
        this.connectionService.deleteConnection(id);
        return ResponseEntity.ok().build();
    }

}
