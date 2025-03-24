package igem.ioannina.backend.controllers;

//import igem.ioannina.backend.dto.HexagonDTO;
import igem.ioannina.backend.models.Connection;
import igem.ioannina.backend.models.Hexagon;
import igem.ioannina.backend.models.Sensor;
import igem.ioannina.backend.services.ConnectionService;
import igem.ioannina.backend.services.HexagonService;
import igem.ioannina.backend.services.SensorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MapController {

    @Autowired
    private HexagonService hexagonService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private ConnectionService connectionService;

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("connections")
    public String connections() {
        Iterable<Connection> conns = connectionService.listAll();
        String text = "";
        for (Connection conn : conns){
            text = text.concat(String.format("Connection: Hexagon: %s ----- Sensor: %s<br>", conn.getHexagonId(), conn.getSensorId()));
        }
        return text;
    }

    @GetMapping("hexagons")
    public String hexagons() {
        Iterable<Hexagon> hexagons = hexagonService.listAll();
        String text = "";
        for (Hexagon hexagon : hexagons){
            text = text.concat(String.format("Hexagon %s: latitude: %s ----- longtitude: %s<br>", hexagon.getId(), hexagon.getLatitude(), hexagon.getLongtitude()));
        }
        return text;
    }

    @GetMapping("sensors")
    public String sensors() {
        Iterable<Sensor> sensors = sensorService.listAll();
        String text = "";
        for (Sensor sensor : sensors){
            text = text.concat(String.format("Sensor %s: reading: %s ----- date: %s<br>", sensor.getId(), sensor.getConcentration(), sensor.getLastReading()));
        }
        return text;
    }

    @GetMapping("hexagon/{id}")
    public ResponseEntity<Float> hexagonData(@PathVariable Integer id) {

        //Hexagon hexagon = hexagonService.get(id);
        Iterable<Connection> conns = connectionService.get(id);
        float sum = 0;
        //String text = String.format("Hexagon with id %s connects to sensors ", id);
        for (Connection conn : conns) {
            Integer sensorId = conn.getSensorId();
            Sensor sensor = sensorService.get(sensorId);
            sum += sensor.getConcentration();
            //text = text.concat(String.format("%s ", sensorId));
        }
        Float averageConcentration = sum / 6;
        //HexagonDTO hexData = new HexagonDTO(hexagon.getId(), hexagon.getLatitude(), hexagon.getLongtitude(), averageConcentration);
        //text = text.concat(String.format(" and has a concentration of %s μg/m<sup>3</sup>", averageConcentration));
        return ResponseEntity.ok(averageConcentration);
    }
    
}