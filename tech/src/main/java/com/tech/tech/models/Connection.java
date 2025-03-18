package com.tech.tech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


@Entity
@IdClass(ConnectionId.class)
public class Connection {
    @Id
    private Integer sensorId;
    @Id
    private Integer hexagonId;


}