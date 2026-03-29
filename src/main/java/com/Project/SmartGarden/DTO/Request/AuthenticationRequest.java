package com.Project.SmartGarden.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}
