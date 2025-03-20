package com.tech.tech.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tech.tech.models.Connection;
import com.tech.tech.repositories.ConnectionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConnectionService {
    private ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Iterable<Connection> listAll() {
        return connectionRepository.findAll();
    }

    public Connection get(Integer id) {
        final Optional<Connection> conn = connectionRepository.FindAllByHexagonId(id);
        return conn.orElse(null);
    }
}
