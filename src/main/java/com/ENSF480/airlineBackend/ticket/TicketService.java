package com.ENSF480.airlineBackend.ticket;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

import com.ENSF480.airlineBackend.payment.PaymentEntry;
import com.ENSF480.airlineBackend.seat.Seat;
import com.ENSF480.airlineBackend.seat.SeatService;
import com.ENSF480.airlineBackend.email.EmailService;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final EmailService emailService;

    public TicketService(TicketRepository ticketRepository, SeatService seatService, EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.emailService = emailService;
    }

    public void processTicketPayment(PaymentEntry paymentEntry) {
        if (!paymentEntry.isValid()) {
            throw new IllegalStateException("Invalid credit card");
        }
        seatService.updateSeatAvailability(paymentEntry.getSeatId(), true);
        Seat seat = seatService.findById(paymentEntry.getSeatId());
        Ticket ticket = new Ticket(paymentEntry.getUser(), seat, paymentEntry.getHasCancellationInsurance());
        ticketRepository.save(ticket);
        try {
            emailService.sendTicketEmail(ticket, false);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("exception: %s", e.getMessage()));
        }
    }

    public ArrayList<Ticket> getTickets(String email) {
        return ticketRepository.findTicketByEmail(email);
    }

    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalStateException("ticket with id " + ticketId + " does not exists"));

        seatService.updateSeatAvailability(ticket.getBookedSeat().getId(), false);
        ticketRepository.deleteById(ticketId);
        try {
            emailService.sendTicketEmail(ticket, true);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("exception: %s", e.getMessage()));
        }
    }

    public ArrayList<Ticket> getTicketsByFlightId(Long flightId) {
        return ticketRepository.findTicketByFlightId(flightId);
    }
}
