package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.PumpLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PumpLogRepository extends JpaRepository<PumpLog, UUID> {
    List<PumpLog> findByPumpId(UUID pumpId);
    List<PumpLog> findByUserId(UUID userId);
}
