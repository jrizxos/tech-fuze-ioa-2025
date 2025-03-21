package com.tech.tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.tech.models.Connection;
import com.tech.tech.repositories.ConnectionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Iterable<Connection> listAll() {
        return connectionRepository.findAll();
    }

    public Iterable<Connection> get(Integer id) {
        return (Iterable<Connection>) connectionRepository.findAllByHexagonId(id);
    }
}
