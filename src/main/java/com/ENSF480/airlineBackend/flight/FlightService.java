package com.ENSF480.airlineBackend.flight;

import org.springframework.stereotype.Service;

import com.ENSF480.airlineBackend.aircraft.Aircraft;
import com.ENSF480.airlineBackend.aircraft.AircraftService;

import java.time.LocalDateTime;
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

    public void createFlight(FlightDetails flightDetails) {
        createFlight(flightDetails.getAircraftId(), flightDetails.getSource(),
        flightDetails.getDestination(), flightDetails.getDepartureTime(),
        flightDetails.getDuration(), flightDetails.getBasePrice());
        
        
    }

    public void createFlight(Long aircraftId, String source, String destination, LocalDateTime departureTime, Duration duration, int basePrice) {
        // Fetch the Aircraft by ID
        Aircraft aircraft = aircraftService.findById(aircraftId).orElseThrow(() -> new RuntimeException("Aircraft not found"));
        
        // Create the new Flight
        Flight flight = new Flight(aircraft, source, destination, departureTime, duration, basePrice);
        flightRepository.save(flight);
     
    }
}
