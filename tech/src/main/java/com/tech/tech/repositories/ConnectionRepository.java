package com.tech.tech.repositories;

import com.tech.tech.models.Connection;
import com.tech.tech.models.ConnectionId;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<Connection, ConnectionId>{
    public Optional<Connection> FindAllByHexagonId(Integer id);
}
