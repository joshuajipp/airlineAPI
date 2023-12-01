package com.ENSF480.airlineBackend.ticket;

import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;

import com.ENSF480.airlineBackend.payment.CreditCard;
import com.ENSF480.airlineBackend.payment.CreditCardRepository;
import com.ENSF480.airlineBackend.payment.PaymentEntry;
import com.ENSF480.airlineBackend.seat.Seat;
import com.ENSF480.airlineBackend.seat.SeatService;
import com.ENSF480.airlineBackend.email.EmailService;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CreditCardRepository creditCardRepository;
    private final SeatService seatService;
    private final EmailService emailService;

    public TicketService(TicketRepository ticketRepository, CreditCardRepository creditCardRepository, SeatService seatService, EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.creditCardRepository = creditCardRepository;
        this.seatService = seatService;
        this.emailService = emailService;
    }

    public void processTicketPayment(PaymentEntry paymentEntry) throws MessagingException {
        Optional<CreditCard> card = creditCardRepository.findById(paymentEntry.getCreditCard().getCardNumber());
        if (!paymentEntry.isValid() && card.isPresent()) {
            throw new IllegalStateException("Invalid credit card");
        }
        Seat seat = seatService.findById(paymentEntry.getSeatId());

        if (!enoughBalance(paymentEntry, paymentEntry.getCreditCard(), seat)){
            throw new TransactionException("Not enough balance on credit card");
        }
        seatService.updateSeatAvailability(paymentEntry.getSeatId(), true);
        Ticket ticket = new Ticket(paymentEntry.getUser(), seat, paymentEntry.getHasCancellationInsurance());
        ticketRepository.save(ticket);
        emailService.sendTicketEmail(ticket, false);
    }

    public boolean enoughBalance(PaymentEntry paymentEntry, CreditCard creditCard, Seat seat){
        double totalPrice = seat.getCalculatedPrice();
        if (paymentEntry.getHasCancellationInsurance()){
            totalPrice = totalPrice * 1.1;
        }
        if (totalPrice <= creditCard.getBalance()){
            CreditCard dbCard = creditCardRepository.findById(creditCard.getCardNumber()).get();
            dbCard.setBalance(dbCard.getBalance() - totalPrice);
            creditCardRepository.save(dbCard);
            return true;
        }
        return false;
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
