package com.ENSF480.airlineBackend.flight;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // CRUD operations (create, read, update, delete)
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }
}
