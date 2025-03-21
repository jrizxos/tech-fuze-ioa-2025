package com.tech.tech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.tech.models.Sensor;
import com.tech.tech.repositories.SensorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Iterable<Sensor> listAll() {
        return sensorRepository.findAll();
    }

    public Sensor get(Integer id){
        final Optional<Sensor> sensor = sensorRepository.findById(id);
        return sensor.orElse(null);
    }
}
