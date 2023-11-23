package com.ENSF480.airlineBackend.seat;


public enum SeatType {

    ORDINARY(1.0),
    COMFORT(1.4),
    BUSINESS_CLASS(2.0);

    private final double PRICE_MULTIPLIER;



    /* Constructor */
    SeatType(double priceMultiplier) {
        this.PRICE_MULTIPLIER = priceMultiplier;
    }

    /* Getters */
    public double getPriceMultiplier() {
        return PRICE_MULTIPLIER;
    }

}