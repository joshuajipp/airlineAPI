package com.ENSF480.airlineBackend.user;

import java.util.ArrayList;

import com.ENSF480.airlineBackend.ticket.Ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class RegisteredUser extends User{
    @Id
    @SequenceGenerator(
        name = "reg_user_sequence",
        sequenceName = "registered_user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "reg_user_sequence"
    )
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private boolean hasFreeCompanionTicket;

    public boolean isHasFreeCompanionTicket() {
        return hasFreeCompanionTicket;
    }

    public void setHasFreeCompanionTicket(boolean hasFreeCompanionTicket) {
        this.hasFreeCompanionTicket = hasFreeCompanionTicket;
    }
    private String monthlyPromotion;
    
    public String getMonthlyPromotion() {
        return monthlyPromotion;
    }

    public void setMonthlyPromotion(String monthlyPromotion) {
        this.monthlyPromotion = monthlyPromotion;
    }
    private ArrayList<Ticket> tickets;
    
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public RegisteredUser(){
        super();
    }

    public RegisteredUser(Long id, String email, String password, String firstName, String lastName){
        super(email, firstName, lastName);
        this.id = id;
        this.password = password;
        this.hasFreeCompanionTicket = true;
        this.tickets = new ArrayList<Ticket>();
    }
    

}
