package igem.ioannina.backend.repositories;

import igem.ioannina.backend.models.Connection;
import igem.ioannina.backend.models.ConnectionId;

import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<Connection, ConnectionId>{
    public Iterable<Connection> findAllByHexagonId(Integer hexagonId);
}
