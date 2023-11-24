package com.ENSF480.airlineBackend.flight;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

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
    private String name;
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
    private ArrayList<Seat> seats; 
    private int basePrice;
    private boolean isAvailable;

    public Flight(Long id, Aircraft aircraft, String name, String source, String destination, LocalDateTime departureTime, Duration duration, ArrayList<Seat> seats, int basePrice, boolean isAvailable) {
        this.id = id;
        this.aircraft = aircraft;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.seats = seats;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
    }

    public Flight(Aircraft aircraft, String name, String source, String destination, LocalDateTime departureTime, Duration duration, ArrayList<Seat> seats, int basePrice, boolean isAvailable) {
        this.aircraft = aircraft;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.seats = seats;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
    }

    public Flight() {
    }

    public Long getId() {
        return this.id;
    }

    public Aircraft getAircraft() {
        return this.aircraft;
    }

    public String getName() {
        return this.name;
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

    public ArrayList<Seat> getSeats() {
        return this.seats;
    }

    public int getBasePrice() {
        return this.basePrice;
    }

    public boolean isIsAvailable() {
        return this.isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Flight id(Long id) {
        this.id = id;
        return this;
    }

    public Flight aircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        return this;
    }

    public Flight name(String name) {
        this.name = name;
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

    public Flight isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", aircraft='" + getAircraft() + "'" +
            ", name='" + getName() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", departureTime='" + getDepartureTime() + "'" +
            ", duration='" + getDuration() + "'" +
            ", seats='" + getSeats() + "'" +
            ", basePrice='" + getBasePrice() + "'" +
            ", isAvailable='" + isIsAvailable() + "'" +
            "}";
    }
}
