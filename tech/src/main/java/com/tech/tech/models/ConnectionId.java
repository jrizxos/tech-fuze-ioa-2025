package com.tech.tech.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ConnectionId implements Serializable {
    private Integer sensorId;

    private Integer hexagonId;

    public ConnectionId() {}

    public ConnectionId(Integer sensorId, Integer hexagonId) {
        this.sensorId = sensorId;
        this.hexagonId = hexagonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionId that = (ConnectionId) o;
        return Objects.equals(hexagonId, that.hexagonId) &&
               Objects.equals(sensorId, that.sensorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hexagonId, sensorId);
    }
}