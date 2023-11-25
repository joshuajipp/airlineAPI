package com.ENSF480.airlineBackend.flight;

import java.time.LocalDateTime;

public class FlightDetails {
    private Long aircraftId;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private int durationInMinutes;
    private int basePrice;

    public FlightDetails() {
    }

    public FlightDetails(Long aircraftId, String source, String destination, String departureTime, int durationInMinutes, int basePrice) {
        this.aircraftId = aircraftId;
        this.source = source;
        this.destination = destination;
        this.basePrice = basePrice;
        this.durationInMinutes = durationInMinutes;
        this.departureTime = LocalDateTime.parse(departureTime);
    }

    public Long getAircraftId() {
        return this.aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
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

    public int getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public int getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
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

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String toString() {
        return "{" +
            " aircraftId='" + getAircraftId() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", departureTime='" + getDepartureTime() + "'" +
            ", duration='" + getDurationInMinutes() + "'" +
            "}";
    }
}
