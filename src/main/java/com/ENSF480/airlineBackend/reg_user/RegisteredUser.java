package com.ENSF480.airlineBackend.reg_user;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class RegisteredUser {
    @Id
    @SequenceGenerator(
        name = "reg_user_sequence",
        sequenceName = "reg_user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "reg_user_sequence"
    )
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int hasFreeCompanionTicket;
    private String monthlyPromotion;

    public RegisteredUser(){
    }

    public RegisteredUser(Long id, String firstName, String lastName, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public RegisteredUser(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.hasFreeCompanionTicket = 1;
        this.monthlyPromotion = (LocalDate.now()).toString();
    }

    public RegisteredUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int isHasFreeCompanionTicket() {
        return hasFreeCompanionTicket;
    }

    public void setHasFreeCompanionTicket(int hasFreeCompanionTicket) {
        this.hasFreeCompanionTicket = hasFreeCompanionTicket;
    }

    public String getMonthlyPromotion() {
        return monthlyPromotion;
    }

    public void setMonthlyPromotion(String monthlyPromotion) {
        this.monthlyPromotion = monthlyPromotion;
    }

}
