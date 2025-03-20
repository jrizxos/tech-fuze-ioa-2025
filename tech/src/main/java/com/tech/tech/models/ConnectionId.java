package com.tech.tech.models;

import java.io.Serializable;


public class ConnectionId implements Serializable {
    private Integer sensorId;

    private Integer hexagonId;

    public ConnectionId(Integer sensorId, Integer hexagonId) {
        this.sensorId = sensorId;
        this.hexagonId = hexagonId;
    }
}