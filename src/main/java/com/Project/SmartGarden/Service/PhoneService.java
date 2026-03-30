package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.Phone;
import com.Project.SmartGarden.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }
    public List<Phone> findByUserId(Integer userId) {
        return phoneRepository.findByUserId(userId);
    }
    public Phone findById(Integer id) {
        return phoneRepository.findById(id).orElse(null);
    }
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }
    public Phone update(Phone phone) {
        return phoneRepository.save(phone);
    }
    public void delete(Integer id) {
        this.phoneRepository.deleteById(id);
    }
}
