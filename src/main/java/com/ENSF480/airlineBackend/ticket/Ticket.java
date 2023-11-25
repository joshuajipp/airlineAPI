package com.ENSF480.airlineBackend.ticket;

import com.ENSF480.airlineBackend.flight.Flight;
import com.ENSF480.airlineBackend.seat.Seat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Ticket {
    @Id
    @SequenceGenerator(
        name = "ticket_sequence",
        sequenceName = "ticket_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ticket_sequence"
    )
    private Long id;
    private boolean hasCancelationInsurance;
    private Flight bookedFlight;
    private Seat bookedSeat;
    private String firstName;
    private String lastName;


    @Override
    public String toString(){
        return  "{" +
                "ticketId='" + id + "'" +
                "hasCancelationInsurance='" + hasCancelationInsurance + "'" +
                "bookedFlight";
    }
    
}
