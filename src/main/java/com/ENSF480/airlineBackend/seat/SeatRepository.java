package com.ENSF480.airlineBackend.seat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s FROM Seat s WHERE s.seatNumber = ?1 AND s.flight.id = ?2")
    Optional<Seat> findSeatBySeatNumberAndFlightId(String seatNumber, Long flightId);
    
}