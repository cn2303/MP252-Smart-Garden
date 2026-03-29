package com.Project.SmartGarden.Controller;

import com.Project.SmartGarden.DTO.Request.AuthenticationRequest;
import com.Project.SmartGarden.DTO.Respone.AuthenticationResponse;
import com.Project.SmartGarden.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticaionService) {
        this.authenticationService = authenticaionService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try{
            var result = authenticationService.authenticate(request);
            return ResponseEntity.ok(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
