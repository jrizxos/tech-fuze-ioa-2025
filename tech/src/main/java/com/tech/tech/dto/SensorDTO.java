package com.tech.tech.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class SensorDTO {
    private int id;
    private float concentration;
    private LocalDateTime lastReading;

    public SensorDTO(int id, LocalDateTime lastReading) {
        this.id = id;
        
        this.concentration = concentration;
        this.lastReading = lastReading;
    }

    public int getID() {
        return this.id;
    }

    public float getConcentration() {
        return this.concentration;
    }

    public LocalDateTime getLastReading() {
        return this.lastReading;
    }
}