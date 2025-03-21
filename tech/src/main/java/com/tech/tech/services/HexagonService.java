package com.tech.tech.services;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.tech.tech.models.Hexagon;
import com.tech.tech.repositories.HexagonRepository;

@Service
@Transactional
public class HexagonService {
    @Autowired
    private HexagonRepository hexRepository;

    public HexagonService(HexagonRepository hexRepository) {
        this.hexRepository = hexRepository;
    }

    public Iterable<Hexagon> listAll() {
        return hexRepository.findAll();
    }

    public Hexagon get(Integer id) {
        final Optional<Hexagon> hex = hexRepository.findById(id);
        return hex.orElse(null);
    }
}
