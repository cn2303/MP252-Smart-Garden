package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.DTO.Request.PumpRequest;
import com.Project.SmartGarden.DTO.Respone.PumpResponse;
import com.Project.SmartGarden.Entity.Pump;
import com.Project.SmartGarden.Entity.PumpStatus;
import com.Project.SmartGarden.Mapper.PumpMapper;
import com.Project.SmartGarden.Repository.PumpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PumpService {
    private final PumpRepository pumpRepository;
    private final PumpMapper pumpMapper;
    @Autowired
    public PumpService(PumpRepository pumpRepository,  PumpMapper pumpMapper) {
        this.pumpRepository = pumpRepository;
        this.pumpMapper =  pumpMapper;
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
    public void delete(Integer id){
        this.pumpRepository.deleteById(id);
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
}
