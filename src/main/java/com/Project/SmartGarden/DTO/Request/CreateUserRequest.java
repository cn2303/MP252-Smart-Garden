package com.Project.SmartGarden.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String fullName;
    private String email;
    private String userName;
    private String password;
}
