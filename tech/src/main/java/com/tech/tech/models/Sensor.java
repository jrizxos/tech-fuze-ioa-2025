package com.tech.tech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Float concentration;

    private LocalDateTime lastReading;

    public Integer getId() {
        return id;
    }

    public Float getConcentration() {
        return concentration;
    }

    public LocalDateTime getLastReading() {
        return lastReading;
    }
}