package com.tech.tech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Hexagon {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Float latitude;

    private Float longtitude;

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongtitude() {
        return longtitude;
    }
}
