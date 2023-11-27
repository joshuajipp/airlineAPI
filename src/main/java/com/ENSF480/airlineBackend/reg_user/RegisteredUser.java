package com.ENSF480.airlineBackend.reg_user;

import java.util.ArrayList;
import java.util.List;

// import com.ENSF480.airlineBackend.ticket.Ticket;
import com.ENSF480.airlineBackend.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class RegisteredUser{
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
    // @JsonManagedReference
    // private List<Ticket> tickets;
    
    // public List<Ticket> getTickets() {
    //     return tickets;
    // }

    // public void setTickets(List<Ticket> tickets) {
    //     this.tickets = tickets;
    // }
    
    private String email;

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RegisteredUser(){
        
    }

    public RegisteredUser(String email, String firstName, String lastName, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.hasFreeCompanionTicket = true;
        // this.tickets = new ArrayList<Ticket>();
    }

    public RegisteredUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public RegisteredUser(Long id, String email, String password, String firstName, String lastName){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
        this.hasFreeCompanionTicket = true;
        // this.tickets = new ArrayList<Ticket>();
    }
    

}
