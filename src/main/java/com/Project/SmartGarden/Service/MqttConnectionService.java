package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.*;
import com.Project.SmartGarden.Mapper.ConnectionMapper;
import com.Project.SmartGarden.Repository.*;
import com.Project.SmartGarden.utils.ConnectionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class MqttConnectionService {
    private final SensorDataRepository sensorDataRepository;
    private final PumpRepository pumpRepository;
    private final ObjectMapper objectMapper;
    private final DeviceRepository deviceRepository;
    private final AlertRepository alertRepository;
    private final ConnectionManager connectionManager;
    private final ConnectionRepository connectionRepository;
    private final PumpLogRepository pumpLogRepository;
    @Autowired
    public MqttConnectionService(ObjectMapper objectMapper,
                                 SensorDataRepository sensorDataRepository,
                                 PumpRepository pumpRepository,
                                 DeviceRepository deviceRepository,
                                 AlertRepository alertRepository,
                                 ConnectionManager connectionManager,
                                 ConnectionRepository connectionRepository,
                                 PumpLogRepository pumpLogRepository) {
        this.objectMapper = objectMapper;
        this.sensorDataRepository = sensorDataRepository;
        this.pumpRepository = pumpRepository;
        this.deviceRepository = deviceRepository;
        this.alertRepository = alertRepository;
        this.connectionManager = connectionManager;
        this.connectionRepository = connectionRepository;
        this.pumpLogRepository = pumpLogRepository;
    }
    public MqttClient connect(Connection connection) {
        String broker = connection.getAddrBroker();
        String username = connection.getBrokerName();
        String aioKey = connection.getPassword();

        String topic = connection.getFeed();
        try {
            MemoryPersistence persistence = new MemoryPersistence();

            MqttClient client = new MqttClient(
                    broker,
                    MqttClient.generateClientId(),
                    persistence
            );

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(aioKey.toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            client.connect(options);
            connectionManager.addConnection(connection.getId(), client);
            client.subscribe(topic, (t, msg) -> {
                String payload = new String(msg.getPayload());
                Map<String, Object> data = objectMapper.readValue(payload, Map.class);
                Integer pumpId = (Integer) data.get("pump_id");
                Pump pump = pumpRepository.findById(pumpId).orElse(null);
                if (pump == null) {
                    System.out.println("Pump Not Found");
                    return;
                }
                if (data.containsKey("temperature")) {
                    double value = ((Number) data.get("temperature")).doubleValue();
                    Device device = this.deviceRepository.findByPumpIdAndType(pump.getId(), Type.TEMPERATURE);
                    if (device == null) {
                        System.out.println("Device Not Found");
                        return;
                    }
                    //Save SensorData
                    SensorData sensorData = SensorData.builder()
                            .value(value)
                            .type(Type.TEMPERATURE)
                            .deviceId(device.getId())
                            .unit("C")
                            .createdAt(LocalDateTime.now())
                            .build();
                    this.sensorDataRepository.save(sensorData);
                    //logic push notification SensorData
                    if(value>=pump.getTemperatureMax()) {
                        //logic push notification Alert
                        Alert alert = Alert.builder()
                                .deviceId(device.getId())
                                .type(Type.TEMPERATURE)
                                .value(value)
                                .threshold(pump.getTemperatureMax())
                                .message("Temperature is greater than Threshold: "+ value)
                                .createdAt(LocalDateTime.now())
                                .build();
                        alertRepository.save(alert);
                    }
                    if(value<=pump.getTemperatureMin()) {
                        //logic push notification Alert
                        Alert alert = Alert.builder()
                                .deviceId(device.getId())
                                .type(Type.TEMPERATURE)
                                .value(value)
                                .threshold(pump.getTemperatureMin())
                                .message("Temperature is smaller than Threshold: "+ value)
                                .createdAt(LocalDateTime.now())
                                .build();
                        alertRepository.save(alert);
                    }
                }

                if (data.containsKey("soil_moisture")) {
                    double value = ((Number) data.get("soil_moisture")).doubleValue();
                    Device device = this.deviceRepository.findByPumpIdAndType(pump.getId(), Type.MOISTURE);
                    if (device == null) {
                        System.out.println("Device Not Found");
                        return;
                    }
                    //Save SensorData
                    SensorData sensorData = SensorData.builder()
                            .value(value)
                            .type(Type.MOISTURE)
                            .deviceId(device.getId())
                            .unit("%")
                            .createdAt(LocalDateTime.now())
                            .build();
                    this.sensorDataRepository.save(sensorData);
                    //logic push notification SensorData
                    if(value<pump.getMoistureThreshold()) {
                        //logic push notification Alert
                        Alert alert = Alert.builder()
                                .deviceId(device.getId())
                                .type(Type.MOISTURE)
                                .value(value)
                                .threshold(pump.getMoistureThreshold())
                                .message("Moisture is smaller than Threshold: "+ value)
                                .createdAt(LocalDateTime.now())
                                .build();
                        alertRepository.save(alert);
                        //Logic automation Pump
                        double waterVolume = (pump.getFieldCapacity() - value) * pump.getRootDepth() * pump.getArea();

                        autoControl(pump,waterVolume);
                    }
                }

                if (data.containsKey("light")) {
                    double value = ((Number) data.get("light")).doubleValue();
                    //logic push notification
                    Device device = this.deviceRepository.findByPumpIdAndType(pump.getId(), Type.LIGHT);
                    if (device == null) {
                        System.out.println("Device Not Found");
                        return;
                    }
                    //Save SensorData
                    SensorData sensorData = SensorData.builder()
                            .value(value)
                            .type(Type.LIGHT)
                            .deviceId(device.getId())
                            .unit("lux")
                            .createdAt(LocalDateTime.now())
                            .build();
                    this.sensorDataRepository.save(sensorData);
                    //logic push notification SensorData
                    if(value>=pump.getLightIntensityMax()) {
                        //logic push notification Alert
                        Alert alert = Alert.builder()
                                .deviceId(device.getId())
                                .type(Type.LIGHT)
                                .value(value)
                                .threshold(pump.getLightIntensityMax())
                                .message("Light Intensity is greater than Threshold: "+ value)
                                .createdAt(LocalDateTime.now())
                                .build();
                        alertRepository.save(alert);
                    }
                }
                if (data.containsKey("action")) {
                    String status = data.get("action").toString();
                    if(status=="success_off") {
                       Pump pump1 =  this.pumpRepository.findById(pump.getId()).orElse(null);
                       pump1.setStatus(PumpStatus.OFF);
                       this.pumpRepository.save(pump1);
                    }
                }
            });
            return client;
        }
        catch (MqttException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void autoControl(Pump pump,double waterVolume) throws MqttException, JsonProcessingException {
        //Message Sent
        Map<String, Object> json = new HashMap<>();
        json.put("action","swopen");
        json.put("water_volume",waterVolume);
        json.put("pump_id",pump.getId());
        String jsonObject = objectMapper.writeValueAsString(json);
        MqttMessage message = new MqttMessage(jsonObject.getBytes());
        message.setQos(1);
        Connection connection = connectionRepository.findById(pump.getConnectionId()).orElse(null);
        if (connection == null) {
            throw new RuntimeException("Connection Not Found");
        }
        MqttClient client = connectionManager.getConnection(connection.getId());
        if(client == null) {
            throw new RuntimeException(" MQTT Connection Not Found");
        }
        client.publish(connection.getFeed(), message);
        //Pump Log
        PumpLog pumpLog = PumpLog.builder()
                .pumpId(pump.getId())
                .userId(pump.getUserId())
                .action(PumpStatus.ON)
                .mode(Mode.AUTO)
                .waterVolume(waterVolume)
                .status(ActionStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .build();
        this.pumpLogRepository.save(pumpLog);
        //Update Pump Status
        Pump pump1 = this.pumpRepository.findById(pump.getId()).orElse(null);
        pump1.setStatus(PumpStatus.ON);
        this.pumpRepository.save(pump1);
    }
    public void manualControlOn(Pump pump) throws MqttException, JsonProcessingException {
        //Message Sent
        Map<String, Object> json = new HashMap<>();
        json.put("action","sopen");
        json.put("pump_id",pump.getId());
        String jsonObject = objectMapper.writeValueAsString(json);
        MqttMessage message = new MqttMessage(jsonObject.getBytes());
        message.setQos(1);
        MqttClient client = this.connectionManager.getConnection(pump.getConnectionId());
        Connection connection = connectionRepository.findById(pump.getConnectionId()).orElse(null);
        if (connection == null) {
            return;
        }
        client.publish(connection.getFeed(), message);
        //Pump Log
        PumpLog pumpLog = PumpLog.builder()
                .pumpId(pump.getId())
                .userId(pump.getUserId())
                .action(PumpStatus.ON)
                .mode(Mode.MANUAL)
                .waterVolume(0.0)
                .status(ActionStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .build();
        this.pumpLogRepository.save(pumpLog);
        //Update Pump Status
        Pump pump1 = this.pumpRepository.findById(pump.getId()).orElse(null);
        pump1.setStatus(PumpStatus.ON);
        this.pumpRepository.save(pump1);
    }
    public void manualControlOff(Pump pump) throws MqttException, JsonProcessingException {
        //Message Sent
        Map<String, Object> json = new HashMap<>();
        json.put("action","sclose");
        json.put("pump_id",pump.getId());
        String jsonObject = objectMapper.writeValueAsString(json);
        MqttMessage message = new MqttMessage(jsonObject.getBytes());
        message.setQos(1);
        MqttClient client = this.connectionManager.getConnection(pump.getConnectionId());
        Connection connection = connectionRepository.findById(pump.getConnectionId()).orElse(null);
        if (connection == null) {
            return;
        }
        client.publish(connection.getFeed(), message);
        //Pump Log
        PumpLog pumpLog = PumpLog.builder()
                .pumpId(pump.getId())
                .userId(pump.getUserId())
                .action(PumpStatus.OFF)
                .mode(Mode.MANUAL)
                .waterVolume(0.0)
                .status(ActionStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .build();
        this.pumpLogRepository.save(pumpLog);
        //Update Pump Status
        Pump pump1 = this.pumpRepository.findById(pump.getId()).orElse(null);
        pump1.setStatus(PumpStatus.OFF);
        this.pumpRepository.save(pump1);
    }
}

