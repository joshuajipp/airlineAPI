package com.ENSF480.airlineBackend.flight;

import org.springframework.stereotype.Service;

import com.ENSF480.airlineBackend.aircraft.Aircraft;
import com.ENSF480.airlineBackend.aircraft.AircraftService;
import com.ENSF480.airlineBackend.seat.Seat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;


@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AircraftService aircraftService;

    public FlightService(FlightRepository flightRepository, AircraftService aircraftService) {
        this.flightRepository = flightRepository;
        this.aircraftService = aircraftService;
    }



    // CRUD operations (create, read, update, delete)
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight createFlight(Long aircraftId, String name, String source, String destination, LocalDateTime departureTime, Duration duration, ArrayList<Seat> seats, int basePrice, boolean isAvailable) {
        // Fetch the Aircraft by ID
        Aircraft aircraft = aircraftService.findById(aircraftId).orElseThrow(() -> new RuntimeException("Aircraft not found"));
        
        // Create and return the new Flight
        return new Flight(aircraft, name, source, destination, departureTime, duration, seats, basePrice, isAvailable);
    }
}
