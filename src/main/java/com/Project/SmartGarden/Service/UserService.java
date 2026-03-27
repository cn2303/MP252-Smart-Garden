package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.CreateUserRequest;
import com.Project.SmartGarden.DTO.Request.UpdateUserRequest;
import com.Project.SmartGarden.DTO.Respone.UserRespone;
import com.Project.SmartGarden.Entity.User;
import com.Project.SmartGarden.Mapper.UserMapper;
import com.Project.SmartGarden.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    final private UserRepository userRepository;
    final private UserMapper userMapper;

    public UserService(UserRepository userRepository,  UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    //Just for testing
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public UserRespone getUserById(UUID id) {
        User user =  userRepository.findById(id).orElse(null);
        return userMapper.toDTO(user);
    }
    public UserRespone createUser(CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        User returnUser = this.userRepository.save(user);
        return userMapper.toDTO(returnUser);
    }
    public UserRespone updateUser(UUID id, UpdateUserRequest request) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        this.userRepository.save(user);
        return userMapper.toDTO(user);
    }
    public void deleteUser(UUID id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            return;
        }
        this.userRepository.delete(user);
    }
}
