package com.Project.SmartGarden.Mapper;

import com.Project.SmartGarden.DTO.Request.ConnectionRequest;
import com.Project.SmartGarden.DTO.Respone.ConnectionResponse;
import com.Project.SmartGarden.Entity.Connection;
import org.springframework.stereotype.Component;

@Component
public class ConnectionMapper {
    public ConnectionResponse toDTO(Connection connection) {
        if (connection == null) {
            return null;
        }
        ConnectionResponse connectionResponse = new ConnectionResponse();
        connectionResponse.setConnectionId(connection.getId());
        connectionResponse.setUserId(connection.getUserId());
        connectionResponse.setBroker_name(connection.getBrokerName());
        connectionResponse.setFeed(connection.getFeed());
        connectionResponse.setAddr(connection.getAddrBroker());
        return connectionResponse;
    }
    public Connection toEntity(ConnectionRequest request) {
        Connection connection = new Connection();
        connection.setUserId(request.getUserId());
        connection.setBrokerName(request.getBrokerName());
        connection.setFeed(request.getFeed());
        connection.setPassword(request.getPassword());
        connection.setAddrBroker(request.getAddress());
        return connection;
    }
}
