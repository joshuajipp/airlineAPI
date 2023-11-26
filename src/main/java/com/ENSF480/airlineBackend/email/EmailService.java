package com.ENSF480.airlineBackend.email;

import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.stereotype.Service;
import java.util.Properties;
import com.ENSF480.airlineBackend.ticket.Ticket;

@Service
public class EmailService {

    public void sendTicketEmail(Ticket ticket) throws MessagingException {
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

        Message message = prepareMessage(session, senderEmail, ticket);

        Transport.send(message);
    }

    private Message prepareMessage(Session session, String senderEmail, Ticket ticket) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ticket.getEmail()));
        message.setSubject("Your Ticket Information");
        String emailText = "Dear " + ticket.getFirstName() + ",\n\n"
                + "Here is your ticket information:\n"
                + "Seat Number: " + ticket.getBookedSeat().getSeatNumber() + "\n"
                + "Class: " + ticket.getBookedSeat().getSeatType() + "\n"
                + "Price: $" + ticket.getBookedSeat().getCalculatedPrice() + "\n\n"
                + "Thank you for choosing us.";
        message.setText(emailText);
        return message;
    }
}
