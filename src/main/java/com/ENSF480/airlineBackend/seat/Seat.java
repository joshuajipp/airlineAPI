package com.ENSF480.airlineBackend.seat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Seat {
   @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    Long id;
    SeatType seatType;
    boolean isReserved;
    double basePrice;

    @Transient
    double calculatedPrice;

    public Seat() {
    }

    public Seat(SeatType seatType, boolean isReserved, double basePrice) {
        this.seatType = seatType;
        this.isReserved = isReserved;
        this.basePrice = basePrice;
    }

    public Seat(Long id, SeatType seatType, boolean isReserved, double basePrice) {
        this.id = id;
        this.seatType = seatType;
        this.isReserved = isReserved;
        this.basePrice = basePrice;
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

    public SeatType getSeatType() {
        return this.seatType;
    }

    public boolean isIsReserved() {
        return this.isReserved;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setId(Long id) {
        this.id = id;
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
            ", isReserved='" + isIsReserved() + "'" +
            ", basePrice='" + getBasePrice() + "'" +
            "}";
    }

    
}
