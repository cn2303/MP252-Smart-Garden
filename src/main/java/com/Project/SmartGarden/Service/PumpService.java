package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.ConfigRequest;
import com.Project.SmartGarden.DTO.Request.PumpRequest;
import com.Project.SmartGarden.DTO.Respone.PumpResponse;
import com.Project.SmartGarden.Entity.Pump;
import com.Project.SmartGarden.Entity.PumpStatus;
import com.Project.SmartGarden.Mapper.PumpMapper;
import com.Project.SmartGarden.Repository.PumpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PumpService {
    private final PumpRepository pumpRepository;
    private final PumpMapper pumpMapper;
    private final MqttConnectionService mqttConnectionService;
    @Autowired
    public PumpService(PumpRepository pumpRepository,  PumpMapper pumpMapper, MqttConnectionService mqttConnectionService) {
        this.pumpRepository = pumpRepository;
        this.pumpMapper =  pumpMapper;
        this.mqttConnectionService = mqttConnectionService;
    }
    public List<PumpResponse> findAll(){
        List<Pump> pumps = pumpRepository.findAll();
        List<PumpResponse> pumpResponses = new ArrayList<>();
        for (Pump pump : pumps) {
            pumpResponses.add(this.pumpMapper.toDTO(pump));
        }
        return pumpResponses;
    }
    public PumpResponse getByPumpId(Integer pumpId) {
        Pump pump = this.pumpRepository.findById(pumpId).orElse(null);
        return pumpMapper.toDTO(pump);
    }
    public List<PumpResponse> getPumpByUserId(Integer id) {
        List<Pump> pumps = this.pumpRepository.findByUserId(id);
        List<PumpResponse> pumpResponseList = new ArrayList<>();
        for (Pump pump : pumps) {
            PumpResponse pumpResponse = this.pumpMapper.toDTO(pump);
            pumpResponseList.add(pumpResponse);
        }
        return pumpResponseList;
    }
    public PumpResponse save(PumpRequest request){
        Pump pump = pumpMapper.toEntity(request);
        Pump returnPump = this.pumpRepository.save(pump);
        return pumpMapper.toDTO(returnPump);
    }
    public PumpResponse updateConfig(ConfigRequest request){
        Pump pump = this.pumpRepository.findById(request.getId()).orElse(null);
        pump.setTemperatureMax(request.getTemperatureMax());
        pump.setTemperatureMin(request.getTemperatureMin());
        pump.setLightIntensityMax(request.getLightIntensityMax());
        pump.setMoistureThreshold(request.getMoistureThreshold());
        pump.setRootDepth(request.getRootDepth());
        pump.setFieldCapacity(request.getFieldCapacity());
        pump.setArea(request.getArea());
        pump.setUpdatedAt(LocalDateTime.now());
        pumpRepository.save(pump);
        return pumpMapper.toDTO(pump);
    }
    public void delete(Integer id){
        try {
            this.pumpRepository.deleteById(id);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public PumpResponse changePumpStatus(Integer id, PumpStatus pumpStatus){
        Pump pump = this.pumpRepository.findById(id).orElse(null);
        if(pump == null){
            return null;
        }
        pump.setStatus(pumpStatus);
        Pump returnPump = this.pumpRepository.save(pump);
        return this.pumpMapper.toDTO(returnPump);
    }
    //update Pump
    public void manualControl(Integer id, boolean onCommand) throws MqttException, JsonProcessingException {
        Pump pump = this.pumpRepository.findById(id).orElse(null);
        if(pump == null){
            return;
        }
        if(onCommand){
            mqttConnectionService.manualControlOn(pump);
        }
        else {
            mqttConnectionService.manualControlOff(pump);
        }

    }
}
