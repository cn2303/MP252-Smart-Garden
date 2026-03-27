package com.Project.SmartGarden.DTO.Respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRespone {
    UUID id;
    String username;
    String email;
    String fullName;
    LocalDateTime createdAt;
}
