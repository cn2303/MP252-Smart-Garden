package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.Alert;
import com.Project.SmartGarden.Repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlertService {
    private final AlertRepository alertRepository;
    @Autowired
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }
    public Alert save(Alert alert) {
        return this.alertRepository.save(alert);
    }
    public Alert getById(Integer id) {
        return this.alertRepository.findById(id).orElse(null);
    }
    public Alert changeIsRead(Integer id) {
        Alert alert = this.alertRepository.findById(id).orElse(null);
        if (alert == null) {
            return null;
        }
        alert.setRead(true);
        return this.alertRepository.save(alert);
    }
}
