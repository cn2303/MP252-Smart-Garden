package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.PumpLog;
import com.Project.SmartGarden.Repository.PumpLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PumpLogService {
    private final PumpLogRepository pumpLogRepository;
    @Autowired
    public PumpLogService(PumpLogRepository pumpLogRepository) {
        this.pumpLogRepository = pumpLogRepository;
    }
    public PumpLog save(PumpLog pumpLog) {
        return this.pumpLogRepository.save(pumpLog);
    }
    public PumpLog getById(UUID id) {
        return this.pumpLogRepository.findById(id).orElse(null);
    }
    public List<PumpLog> getByPumpId(UUID pumpId) {
        return this.pumpLogRepository.findByPumpId(pumpId);
    }
    public List<PumpLog> getByUserId(UUID userId) {
        return this.pumpLogRepository.findByUserId(userId);
    }
}
