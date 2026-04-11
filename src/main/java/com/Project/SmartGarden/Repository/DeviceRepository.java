package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.Device;
import com.Project.SmartGarden.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findByPumpId(Integer pumpId);
    Device findByPumpIdAndType(Integer pumpId, Type type);
    List<Device> findByConnectId(Integer id);
}
