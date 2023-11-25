package com.ENSF480.airlineBackend.flight;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Mapping methods (GET, POST, PUT, DELETE)
    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping(path = "{flightId}")
    public Flight getFlight(@PathVariable("flightId") Long flightId) {
        return flightService.getFlight(flightId);
    }

    @PostMapping
    public void createFlight(@RequestBody FlightDetails flight) {
        flightService.createFlight(flight);
    }
}
