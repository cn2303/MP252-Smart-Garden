package com.Project.SmartGarden.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConnectionManager {
    private final Map<Integer, MqttClient> connections = new ConcurrentHashMap<>();
    public void addConnection(Integer connId, MqttClient mqttClient) {
        connections.put(connId, mqttClient);
    }
    public MqttClient getConnection(Integer connId) {
        return connections.get(connId);
    }
    public void removeConnection(Integer connId) {
        connections.remove(connId);
    }
    public Map<Integer, MqttClient> getConnections() {
        return connections;
    }
}
