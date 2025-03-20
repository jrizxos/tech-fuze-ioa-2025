package com.tech.tech.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.tech.tech.models.Hexagon;
import com.tech.tech.repositories.HexRepository;

@Service
@Transactional
public class HexagonService {
    private HexRepository hexRepository;

    public HexagonService(HexRepository hexRepository) {
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
