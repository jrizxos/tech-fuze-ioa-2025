package com.tech.tech.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech.tech.models.Sensor;

    public interface SensorRepository extends CrudRepository<Sensor, Integer>{

    }
