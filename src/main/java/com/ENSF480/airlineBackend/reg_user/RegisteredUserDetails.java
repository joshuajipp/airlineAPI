package com.ENSF480.airlineBackend.reg_user;

import java.util.ArrayList;

import com.ENSF480.airlineBackend.ticket.Ticket;

public class RegisteredUserDetails extends RegisteredUser {
    
    public RegisteredUserDetails(){
        super();
    }

    public RegisteredUserDetails(String email, String firstName, String lastName, Long id, String password, boolean hasFreeCompanionTicket, String monthlyPromotion, ArrayList<Ticket> tickets){
        super(id, email, password, firstName, lastName);
        this.setHasFreeCompanionTicket(hasFreeCompanionTicket);
        this.setMonthlyPromotion(monthlyPromotion);
        this.setTickets(tickets);
    }
}
