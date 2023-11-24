package com.ENSF480.airlineBackend.flight;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import com.ENSF480.airlineBackend.aircraft.Aircraft;
import com.ENSF480.airlineBackend.seat.Seat;
import jakarta.persistence.CascadeType;
import com.ENSF480.airlineBackend.seat.SeatType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
        name = "flight_sequence",
        sequenceName = "flight_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "flight_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id") // assuming 'id' is the primary key in Aircraft
    private Aircraft aircraft;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private Duration duration; 

    @OneToMany(
        mappedBy = "flight",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Seat> seats; 
    private int basePrice;

    public Flight(Long id, Aircraft aircraft, String source, String destination, LocalDateTime departureTime, Duration duration, int basePrice) {
        this.id = id;
        this.aircraft = aircraft;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.basePrice = basePrice;
        this.seats = new ArrayList<Seat>();
        createSeatsFromAircraft(aircraft);

    }

    public Flight(Aircraft aircraft, String source, String destination, LocalDateTime departureTime, Duration duration, int basePrice) {
        this.aircraft = aircraft;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.basePrice = basePrice;
        this.seats = new ArrayList<Seat>();
        createSeatsFromAircraft(aircraft);
    }

    public Flight() {
    }

    public Long getId() {
        return this.id;
    }

    public Aircraft getAircraft() {
        return this.aircraft;
    }


    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public int getBasePrice() {
        return this.basePrice;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }


    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }


    public Flight id(Long id) {
        this.id = id;
        return this;
    }

    public Flight aircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        return this;
    }


    public Flight source(String source) {
        this.source = source;
        return this;
    }

    public Flight destination(String destination) {
        this.destination = destination;
        return this;
    }

    public Flight departureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public Flight duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Flight seats(ArrayList<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Flight basePrice(int basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", aircraft='" + getAircraft() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", departureTime='" + getDepartureTime() + "'" +
            ", duration='" + getDuration() + "'" +
            ", seats='" + getSeats() + "'" +
            ", basePrice='" + getBasePrice() + "'" +
            "}";
    }

    private void createSeatsFromAircraft(Aircraft aircraft) {
        int numBusinessSeats = aircraft.getBusinessSeatCount();
        int numComfortSeats = aircraft.getComfortSeatCount();
        int numOrdinarySeats = aircraft.getOrdinarySeatCount();

        for (int i = 0; i < numBusinessSeats; i++) {
            Seat seat = new Seat(SeatType.BUSINESS_CLASS, String.format("B%d", i), false, this.basePrice, this);
            this.seats.add(seat);
        }
        for (int i = 0; i < numComfortSeats; i++) {
            Seat seat = new Seat(SeatType.COMFORT, String.format("C%d", i), false, this.basePrice, this);
            this.seats.add(seat);
        }
        for (int i = 0; i < numOrdinarySeats; i++) {
            Seat seat = new Seat(SeatType.ORDINARY, String.format("O%d", i), false, this.basePrice, this);
            this.seats.add(seat);
        }
    }
}
