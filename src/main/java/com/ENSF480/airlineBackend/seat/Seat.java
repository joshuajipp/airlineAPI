package com.ENSF480.airlineBackend.seat;

import com.ENSF480.airlineBackend.flight.Flight;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Seat {
    @Id
    @SequenceGenerator(
        name = "seat_sequence",
        sequenceName = "seat_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seat_sequence"
    )
    Long id;
    String seatNumber;
    SeatType seatType;
    boolean isReserved;
    double basePrice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

    @Transient
    double calculatedPrice;

    public Seat() {
    }

    public Seat(SeatType seatType, String seatNumber, boolean isReserved, double basePrice, Flight flight) {
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
        this.basePrice = basePrice;
        this.flight = flight;
    }

    public Seat(Long id, String seatNumber, SeatType seatType, boolean isReserved, double basePrice, Flight flight) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.isReserved = isReserved;
        this.basePrice = basePrice;
        this.flight = flight;
    }

    public double getCalculatedPrice() {
        return this.basePrice * this.seatType.getPriceMultiplier();
    }

    public void setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public Long getId() {
        return this.id;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public boolean isReserved() {
        return this.isReserved;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Seat id(Long id) {
        setId(id);
        return this;
    }

    public Seat seatType(SeatType seatType) {
        setSeatType(seatType);
        return this;
    }

    public Seat isReserved(boolean isReserved) {
        setIsReserved(isReserved);
        return this;
    }

    public Seat basePrice(double basePrice) {
        setBasePrice(basePrice);
        return this;
    }





    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", seatType='" + getSeatType() + "'" +
            ", isReserved='" + isReserved() + "'" +
            ", basePrice='" + getBasePrice() + "'" +
            "}";
    }

    
}