package com.tech.tech.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.tech.tech.models.Hexagon;
import com.tech.tech.repositories.HexRepository;

@Service
@Transactional
public class HexagonService {
    private HexRepository hexRepository;

    @Autowired
    public HexagonService(HexRepository hexRepository) {
        this.hexRepository = hexRepository;
    }

    public Iterable<Hexagon> listAll() {
        return hexRepository.findAll();
    }

    public Hexagon get(Integer id) {
        final Optional<Hexagon> todo = hexRepository.findById(id);
        return todo.orElse(null);
    }
}
