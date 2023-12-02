package com.ENSF480.airlineBackend.flight;


import java.time.LocalDateTime;
import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.departureTime > ?1")
    ArrayList<Flight> findNonExpiredFlights(LocalDateTime date);
    
}