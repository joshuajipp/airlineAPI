package com.ENSF480.airlineBackend.ticket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import com.ENSF480.airlineBackend.payment.PaymentEntry;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // @PostMapping
    // public void processTicketPayment(@RequestBody PaymentEntry paymentEntry) {
    //     ticketService.processTicketPayment(paymentEntry);
    // }

    @GetMapping(path = "{email}")
    public ArrayList<Ticket> getTickets(@PathVariable("email") String email) {
        return ticketService.getTickets(email);
    }

    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(ticketId);
    }

    
}