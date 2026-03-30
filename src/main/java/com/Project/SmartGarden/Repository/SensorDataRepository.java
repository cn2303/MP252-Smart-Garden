package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
    List<SensorData> findByDeviceIdAndCreatedAtBetween(Integer deviceId, LocalDateTime start, LocalDateTime end);
    List<SensorData> findByDeviceId(Integer deviceId);
}
