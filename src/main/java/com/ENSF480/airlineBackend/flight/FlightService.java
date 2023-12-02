package com.ENSF480.airlineBackend.flight;

import org.springframework.stereotype.Service;

import com.ENSF480.airlineBackend.aircraft.Aircraft;
import com.ENSF480.airlineBackend.aircraft.AircraftService;
import com.ENSF480.airlineBackend.ticket.TicketService;
import com.ENSF480.airlineBackend.ticket.Ticket;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AircraftService aircraftService;
    private final TicketService ticketService;

    public FlightService(FlightRepository flightRepository, AircraftService aircraftService, TicketService ticketService) {
        this.flightRepository = flightRepository;
        this.aircraftService = aircraftService;
        this.ticketService = ticketService;
    }



    // CRUD operations (create, read, update, delete)
    public List<Flight> getFlights() {
        // return all flights where the departure time is after the current time
        LocalDateTime now = LocalDateTime.now();
        return flightRepository.findNonExpiredFlights(now);
    }

    public Flight getFlight(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public void createFlight(FlightDetails flightDetails) {
        createFlight(flightDetails.getAircraftId(), flightDetails.getSource(),
        flightDetails.getDestination(), flightDetails.getDepartureTime(),
        flightDetails.getDurationInMinutes(), flightDetails.getBasePrice());
        
        
    }

    public void createFlight(Long aircraftId, String source, String destination, LocalDateTime departureTime, int duration, int basePrice) {
        // Fetch the Aircraft by ID
        Aircraft aircraft = aircraftService.findById(aircraftId).orElseThrow(() -> new RuntimeException("Aircraft not found"));
        
        // Create the new Flight
        Flight flight = new Flight(aircraft, source, destination, departureTime, duration, basePrice);
        flightRepository.save(flight);
     
    }

    public List<FlightPassengers> getFlightPassengers(Long flightId) {
        ArrayList<Ticket> tickets = ticketService.getTicketsByFlightId(flightId);
        ArrayList<FlightPassengers> flightPassengers = new ArrayList<FlightPassengers>();
        for (Ticket ticket : tickets) {
            flightPassengers.add(new FlightPassengers(ticket.getFirstName(), ticket.getLastName(), ticket.getBookedSeat()));
        }
        return flightPassengers;
    }

    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }
}
