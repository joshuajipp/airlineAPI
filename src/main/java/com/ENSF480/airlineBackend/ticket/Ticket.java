package com.ENSF480.airlineBackend.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.ENSF480.airlineBackend.seat.Seat;
import com.ENSF480.airlineBackend.user.User;

@Entity
@Table
public class Ticket {
    @Id
    @SequenceGenerator(
        name = "ticket_sequence",
        sequenceName = "ticket_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ticket_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean hasCancellationInsurance;

    @OneToOne
    private Seat bookedSeat;

    public Ticket() {
    }

    public Ticket(String firstName, String lastName, String email, boolean hasCancellationInsurance, Seat bookedSeat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hasCancellationInsurance = hasCancellationInsurance;
        this.bookedSeat = bookedSeat;
    }

    public Ticket(Long id, String firstName, String lastName, String email, boolean hasCancellationInsurance, Seat bookedSeat) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hasCancellationInsurance = hasCancellationInsurance;
        this.bookedSeat = bookedSeat;
    }

    public Ticket(User user, Seat bookedSeat) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.hasCancellationInsurance = false;
        this.bookedSeat = bookedSeat;
    }

    public Ticket(User user, Seat bookedSeat, boolean hasCancellationInsurance) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.hasCancellationInsurance = hasCancellationInsurance;
        this.bookedSeat = bookedSeat;
    }

    public Seat getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(Seat bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public boolean isHasCancellationInsurance() {
        return hasCancellationInsurance;
    }

    public void setHasCancellationInsurance(boolean hasCancellationInsurance) {
        this.hasCancellationInsurance = hasCancellationInsurance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ticketId) {
        this.id = ticketId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String ticketFirstName) {
        this.firstName = ticketFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ticketLastName) {
        this.lastName = ticketLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String ticketEmail) {
        this.email = ticketEmail;
    }

    

}
