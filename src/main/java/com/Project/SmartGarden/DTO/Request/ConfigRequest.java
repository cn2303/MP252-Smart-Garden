package com.Project.SmartGarden.DTO.Request;

import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRequest {
    private Integer id;
    private double temperatureMax;
    private double temperatureMin;
    private double lightIntensityMax;
    private double moistureThreshold;
    private double fieldCapacity;
    private double rootDepth;
    private double area;
}
