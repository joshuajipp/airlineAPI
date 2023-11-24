package com.ENSF480.airlineBackend.flight;

import java.time.Duration;
import java.time.LocalDateTime;

public class FlightDetails {
    private Long aircraftId;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private Duration duration;
    private int basePrice;

    public FlightDetails() {
    }

    public FlightDetails(Long aircraftId, String source, String destination, String departureTime, Long duration, int basePrice) {
        this.aircraftId = aircraftId;
        this.source = source;
        this.destination = destination;
        this.basePrice = basePrice;

        // Parse the departureTime and duration strings into LocalDateTime and Duration objects
        this.departureTime = LocalDateTime.parse(departureTime);
        this.duration = Duration.ofHours(duration);
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

    public Duration getDuration() {
        return this.duration;
    
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

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "{" +
            " aircraftId='" + getAircraftId() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", departureTime='" + getDepartureTime() + "'" +
            ", duration='" + getDuration() + "'" +
            "}";
    }
}
