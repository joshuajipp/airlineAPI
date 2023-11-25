package com.ENSF480.airlineBackend.payment;

import com.ENSF480.airlineBackend.user.User;

public class PaymentEntry {
    private User user;
    private Long seatId;
    private boolean hasCancellationInsurance;
    private CreditCard creditCard;

    public PaymentEntry() {
    }

    public PaymentEntry(User user, Long seatId, boolean hasCancellationInsurance, CreditCard creditCard) {
        this.user = user;
        this.seatId = seatId;
        this.hasCancellationInsurance = hasCancellationInsurance;
        this.creditCard = creditCard;
    }

    public PaymentEntry(User user, Long seatId, CreditCard creditCard) {
        this.user = user;
        this.seatId = seatId;
        this.hasCancellationInsurance = false;
        this.creditCard = creditCard;
    }


    public boolean getHasCancellationInsurance() {
        return this.hasCancellationInsurance;
    }

    
    public User getUser() {
        return this.user;
    }

    public Long getSeatId() {
        return this.seatId;
    }

    public CreditCard getCreditCard() {
        return this.creditCard;
    }

    public boolean isValid() {
        return this.creditCard.isValid();
    }
}
