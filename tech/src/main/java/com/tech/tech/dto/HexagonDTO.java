package com.tech.tech.dto;


public class HexagonDTO {
    private int id;
    private float latitude;
    private float longtitude;
    private float concentration;

    public HexagonDTO(int id, float latitude, float longtitude, float concentration) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.concentration = concentration;
    }

    public int getID() {
        return this.id;
    }

    public float getLatitude() {
        return this.latitude;
    }
    
    public float getLongtitude() {
        return this.longtitude;
    }

    public float getConcentration() {
        return concentration;
    }
}