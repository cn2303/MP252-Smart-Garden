package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertRepository  extends JpaRepository<Alert, UUID> {
    List<Alert> findByDeviceId(UUID deviceId);
}
