package igem.ioannina.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import igem.ioannina.backend.models.Sensor;

    public interface SensorRepository extends CrudRepository<Sensor, Integer>{

    }
