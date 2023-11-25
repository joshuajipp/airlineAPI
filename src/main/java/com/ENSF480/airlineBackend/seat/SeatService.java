package com.ENSF480.airlineBackend.seat;

import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void updateSeatAvailability(Long seatId, boolean isReserved) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new IllegalStateException("Seat with id " + seatId + " does not exist"));
        if (seat.isReserved() == isReserved) {
            throw new IllegalStateException("Seat with id " + seatId + " is already " + (isReserved ? "reserved" : "unreserved"));
        }
        seat.setIsReserved(isReserved);
        seatRepository.save(seat);
    }

    public Seat findById(Long seatId) {
        return seatRepository.findById(seatId).orElseThrow(() -> new IllegalStateException("Seat with id " + seatId + " does not exist"));
    }
    
}
