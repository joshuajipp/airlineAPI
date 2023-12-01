package com.ENSF480.airlineBackend.payment;

import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class CreditCard {
    @Id
    private String cardNumber;
    private YearMonth expiryDate;
    private String cvv;
    private double balance = 500.0;

    public CreditCard(){

    }

    public CreditCard(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = YearMonth.parse(expiryDate);
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public YearMonth getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public double getBalance(){
        return balance;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(YearMonth expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    // check if card num is 16 integer digits, expiry date is in the future, and cvv is 3 integer digits
    public boolean isValid() {
        return this.cardNumber.matches("\\d{16}") && this.expiryDate.isAfter(YearMonth.now()) && this.cvv.matches("\\d{3}");
    }

}
