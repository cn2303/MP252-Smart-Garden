package com.Project.SmartGarden.Mapper;

import com.Project.SmartGarden.DTO.Request.CreateUserRequest;
import com.Project.SmartGarden.DTO.Respone.UserRespone;
import com.Project.SmartGarden.Entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserRespone toDTO(User user) {
        if(user == null) return null;
        UserRespone userRespone = new UserRespone();
        userRespone.setId(user.getId());
        userRespone.setEmail(user.getEmail());
        userRespone.setUsername(user.getUsername());
        userRespone.setFullName(user.getFullName());
        userRespone.setCreatedAt(user.getCreatedAt());
        return userRespone;
    }
    public User toEntity(CreateUserRequest request) {
        if(request == null) return null;
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword()); // need to hash
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
}
