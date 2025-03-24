package igem.ioannina.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


@Entity
@IdClass(ConnectionId.class)
public class Connection {
    @Id
    @Column(name = "sensor_id")
    private Integer sensorId;
    @Id
    @Column(name = "hexagon_id")
    private Integer hexagonId;

    public Connection() {}

    public Connection (Integer hexagonId, Integer sensorId) {
        this.hexagonId = hexagonId;
        this.sensorId = sensorId;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public Integer getHexagonId() {
        return hexagonId;
    }
}