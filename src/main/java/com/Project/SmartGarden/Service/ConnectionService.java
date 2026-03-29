package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.ConnectionRequest;
import com.Project.SmartGarden.DTO.Request.ConnectionUpdateRequest;
import com.Project.SmartGarden.DTO.Respone.ConnectionResponse;
import com.Project.SmartGarden.Entity.Connection;
import com.Project.SmartGarden.Mapper.ConnectionMapper;
import com.Project.SmartGarden.Repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ConnectionMapper connectionMapper;
    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository,  ConnectionMapper connectionMapper) {
        this.connectionRepository = connectionRepository;
        this.connectionMapper = connectionMapper;
    }
    public ConnectionResponse getConnectionById(UUID id) {
        Connection connection = this.connectionRepository.findById(id).orElse(null);
        return connectionMapper.toDTO(connection);
    }
    public List<ConnectionResponse> getConnectionByUserId(UUID userId) {
        List<Connection> connections = this.connectionRepository.findByUserId(userId);
        List<ConnectionResponse> responses = new ArrayList<>();
        for (Connection connection : connections) {
            responses.add(connectionMapper.toDTO(connection));
        }
        return responses;
    }
    public ConnectionResponse saveConnection(ConnectionRequest request) {
        Connection connection = this.connectionMapper.toEntity(request);
        Connection returnConnection = this.connectionRepository.save(connection);
        return connectionMapper.toDTO(returnConnection);
    }
    public ConnectionResponse updateConnection(UUID id, ConnectionUpdateRequest request) {
        Connection connection = this.connectionRepository.findById(id).orElse(null);
        if (connection == null) {
            return null;
        }
        connection.setAddrBroker(request.getAddress());
        connection.setPassword(request.getPassword());
        connection.setBrokerName(request.getBrokerName());
        connection.setFeed(request.getFeed());
        Connection returnConnection = this.connectionRepository.save(connection);
        return connectionMapper.toDTO(returnConnection);
    }
    public void deleteConnection(UUID id) {
        this.connectionRepository.deleteById(id);
    }
}
