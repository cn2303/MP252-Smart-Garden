package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.AuthenticationRequest;
import com.Project.SmartGarden.DTO.Respone.AuthenticationResponse;
import com.Project.SmartGarden.Entity.User;
import com.Project.SmartGarden.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.Builder;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    private  String SIGNER_KEY;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = this.userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new RuntimeException("Username not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("Invalid username or password");
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .userId(user.getId())
                .token(token)
                .build();
    }
    public String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(24, ChronoUnit.HOURS).toEpochMilli()))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
        try {
            jwsObject.sign((new MACSigner(SIGNER_KEY.getBytes())));
            return jwsObject.serialize();
        }catch (JOSEException e) {
            throw new RuntimeException("Cannot create Token");
        }
    }
}
