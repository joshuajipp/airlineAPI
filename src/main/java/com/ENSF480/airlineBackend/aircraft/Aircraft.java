package com.ENSF480.airlineBackend.aircraft;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class Aircraft {
    @Id
    @SequenceGenerator(
        name = "aircraft_sequence",
        sequenceName = "aircraft_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "aircraft_sequence"
    )
    private Long id;
    private String name;
    private int businessSeatCount;
    private int comfortSeatCount;
    private int ordinarySeatCount;

    public Aircraft() {
    }


    public Aircraft(String name, int businessSeatCount, int comfortSeatCount, int ordinarySeatCount) {
        this.name = name;
        this.businessSeatCount = businessSeatCount;
        this.comfortSeatCount = comfortSeatCount;
        this.ordinarySeatCount = ordinarySeatCount;
    }


    public Aircraft(Long id, String name, int businessSeatCount, int comfortSeatCount, int ordinarySeatCount) {
        this.id = id;
        this.name = name;
        this.businessSeatCount = businessSeatCount;
        this.comfortSeatCount = comfortSeatCount;
        this.ordinarySeatCount = ordinarySeatCount;
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getBusinessSeatCount() {
        return this.businessSeatCount;
    }

    public int getComfortSeatCount() {
        return this.comfortSeatCount;
    }

    public int getOrdinarySeatCount() {
        return this.ordinarySeatCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public void setBusinessSeatCount(int businessSeatCount) {
        this.businessSeatCount = businessSeatCount;
    }

    public void setComfortSeatCount(int comfortSeatCount) {
        this.comfortSeatCount = comfortSeatCount;
    }

    public void setOrdinarySeatCount(int ordinarySeatCount) {
        this.ordinarySeatCount = ordinarySeatCount;
    }

    public Aircraft id(Long id) {
        setId(id);
        return this;
    }

    public Aircraft name(String name) {
        setName(name);
        return this;
    }

    public Aircraft businessSeatCount(int businessSeatCount) {
        setBusinessSeatCount(businessSeatCount);
        return this;
    }

    public Aircraft comfortSeatCount(int comfortSeatCount) {
        setComfortSeatCount(comfortSeatCount);
        return this;
    }

    public Aircraft ordinarySeatCount(int ordinarySeatCount) {
        setOrdinarySeatCount(ordinarySeatCount);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", businessSeatCount='" + getBusinessSeatCount() + "'" +
            ", comfortSeatCount='" + getComfortSeatCount() + "'" +
            ", ordinarySeatCount='" + getOrdinarySeatCount() + "'" +
            "}";
    }
    
}
