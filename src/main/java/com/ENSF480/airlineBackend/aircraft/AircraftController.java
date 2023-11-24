package com.ENSF480.airlineBackend.aircraft;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAircrafts() {
        return aircraftService.getAircrafts();
    }
    
    @PostMapping
    public void createNewAircraft(@RequestBody Aircraft aircraft) {
        aircraftService.createNewAircraft(aircraft);
    }

    @DeleteMapping(path = "{aircraftId}")
    public void deleteAircraft(@PathVariable("aircraftId") Long aircraftId) {
        aircraftService.deleteAircraft(aircraftId);
    }
}
