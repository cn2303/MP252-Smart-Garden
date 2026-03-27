package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.CreateUserRequest;
import com.Project.SmartGarden.DTO.Request.UpdateUserRequest;
import com.Project.SmartGarden.DTO.Respone.UserRespone;
import com.Project.SmartGarden.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        UserRespone user = this.userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        UserRespone user = this.userService.createUser(request);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id,@RequestBody UpdateUserRequest request) {
        UserRespone user = this.userService.updateUser(id, request);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
