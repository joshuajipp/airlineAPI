package com.ENSF480.airlineBackend.email;

import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.stereotype.Service;
import java.util.Properties;
import com.ENSF480.airlineBackend.ticket.Ticket;

@Service
public class EmailService {

    public void sendTicketEmail(Ticket ticket, boolean isCancellation) throws MessagingException {
        String senderEmail = System.getenv("EMAIL_SENDER");
        String password = System.getenv("PASSWORD_SENDER");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp-mail.outlook.com"); // Updated SMTP host for Outlook
        properties.put("mail.smtp.port", "587"); // Port 587 is typically used for TLS

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });
        if (isCancellation) {
            Message message = prepareCancellationMessage(session, senderEmail, ticket);
            Transport.send(message);
            return;
        }
        Message message = prepareMessage(session, senderEmail, ticket);

        Transport.send(message);
    }

    private Message prepareMessage(Session session, String senderEmail, Ticket ticket) throws MessagingException {
        double cancellationInsurancePrice = 0;
        if (ticket.getHasCancellationInsurance()) {
            cancellationInsurancePrice = ticket.getBookedSeat().getCalculatedPrice() * 0.1;
        }
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ticket.getEmail()));
        message.setSubject("Your Ticket Information");
        String emailText = "Dear " + ticket.getFirstName() + ",\n\n"
                + "Here is your ticket information:\n"
                + "Seat Number: " + ticket.getBookedSeat().getSeatNumber() + "\n"
                + "Class: " + ticket.getBookedSeat().getSeatType() + "\n"
                + "Seat Price: $" + String.format("%.2f", ticket.getBookedSeat().getCalculatedPrice()) + "\n"
                + "Cancellation Insurance price: $" + String.format("%.2f", cancellationInsurancePrice) + "\n"
                + "Total Price: $" + String.format("%.2f", ticket.getBookedSeat().getCalculatedPrice() + cancellationInsurancePrice) + "\n\n"   
                + "Thank you for choosing us.";
        message.setText(emailText);
        return message;
    }

    private Message prepareCancellationMessage(Session session, String senderEmail, Ticket ticket) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ticket.getEmail()));
        message.setSubject("Your Ticket Cancellation Information");
        String emailText = "Dear " + ticket.getFirstName() + ",\n\n"
                + "We are sorry to hear that you have cancelled your ticket.\n"
                + "Here is your ticket cancellation information:\n"
                + "A refund of $" + String.format("%.2f", ticket.getBookedSeat().getCalculatedPrice()) + " has been issued to your credit card.\n\n"
                + "We hope to see you again.";
        message.setText(emailText);
        return message;
    }
}
