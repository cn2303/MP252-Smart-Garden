package com.Project.SmartGarden.Repository;

import com.Project.SmartGarden.Entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ConnectionRepository extends JpaRepository<Connection, UUID> {
    public List<Connection> findByUserId(UUID userId);
}
