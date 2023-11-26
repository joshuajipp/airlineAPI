package com.ENSF480.airlineBackend.flight;

import com.ENSF480.airlineBackend.seat.Seat;

public class FlightPassengers {
    private String firstName;
    private String lastName;
    private Seat bookedSeat;

    public FlightPassengers() {
    }

    public FlightPassengers(String firstName, String lastName, Seat bookedSeat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookedSeat = bookedSeat;
    }

    public Seat getBookedSeat() {
        return bookedSeat;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
