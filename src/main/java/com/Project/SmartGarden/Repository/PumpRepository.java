package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.Pump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PumpRepository extends JpaRepository<Pump, Integer> {
    List<Pump> findByUserId(Integer userId);
    Optional<Pump> findByConnectionId(Integer connectionId);
}
