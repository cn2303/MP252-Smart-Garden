package com.Project.SmartGarden.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pump_logs")
public class PumpLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer id;
    @Column(name = "pump_id")
    private Integer pumpId;
    @Column(name = "user_id")
    private Integer userId;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "action", columnDefinition = "pump_state_enum")
    private PumpStatus action;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "mode" ,columnDefinition = "pump_mode_enum")
    private Mode mode;
    @Column(name = "water_volume")
    private double waterVolume;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "pump_log_status_enum")
    private ActionStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
