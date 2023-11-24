package com.ENSF480.airlineBackend.seat;

import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

}
