package com.tech.tech.controllers;

import com.tech.tech.repositories.ConnectionRepository;
import com.tech.tech.repositories.HexRepository;
import com.tech.tech.repositories.SensorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MapController {

    @Autowired
    private HexRepository hexRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @GetMapping("hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}