package com.tech.tech.dto;

public class ConnectionDTO {
    private int hexId;
    private int sensorId;
    
    public ConnectionDTO(int hexId, int sensorId) {
        this.hexId = hexId;
        this.sensorId = sensorId;
    }

    public int getHexId() {
        return this.hexId;
    }

    public int getSensorId() {
        return this.sensorId;
    }
}