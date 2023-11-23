package com.ENSF480.airlineBackend.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

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
