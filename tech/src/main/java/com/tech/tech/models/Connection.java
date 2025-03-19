package com.tech.tech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Connection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer sensorId;
    private Integer hexagonId;

    public Integer getHexagonId() {
        return hexagonId;
    }

    public Integer getSensorId() {
        return sensorId;
    }
}
