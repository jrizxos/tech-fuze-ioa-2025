package com.tech.tech.controllers;

import com.tech.tech.models.Hexagon;
import com.tech.tech.repositories.HexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class MapController {

    @Autowired
    private HexRepository hexRepository;

    @GetMapping("hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}