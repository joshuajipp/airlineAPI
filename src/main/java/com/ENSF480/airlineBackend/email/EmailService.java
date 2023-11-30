package com.ENSF480.airlineBackend.email;

import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Properties;

import com.ENSF480.airlineBackend.reg_user.RegisteredUser;
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
            if (!ticket.getHasCancellationInsurance()) {
                Message message = prepareCancellationMessageWithoutInsurance(session, senderEmail, ticket);
                Transport.send(message);
                return;
            }
            Message message = prepareCancellationMessage(session, senderEmail, ticket);
            Transport.send(message);
            return;
        }
        Message message = prepareMessage(session, senderEmail, ticket);

        Transport.send(message);
    }

    public void sendPromotionEmail(RegisteredUser user) throws MessagingException {
        String senderEmail = "angelo.troncone@ucalgary.ca";
        String password = "Jetvac4000!";

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
        
        Message message = preparePromotionMessage(session, senderEmail, user);

        Transport.send(message);
    }

    private Message preparePromotionMessage(Session session, String senderEmail, RegisteredUser user) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Your Ticket Information");

        String emailText = "Hello, " + user.getFirstName() + "\n\n"
            + "Here is your promotion for " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", " + Integer.toString(LocalDate.now().getYear()) + "\n\n"
            + "This code gets you a free small Tim Horton's coffe in participating airports" + "\n"
            + "Note: This code is used once per person" + "\n"
            + "TIMS23";
        message.setText(emailText);
        return message;
        
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
                + "A refund of $" + String.format("%.2f", ticket.getBookedSeat().getCalculatedPrice()) + " has been issued to your credit card.\n\n"
                + "We hope to see you another time.\n\n";
        message.setText(emailText);
        return message;
    }

    private Message prepareCancellationMessageWithoutInsurance(Session session, String senderEmail, Ticket ticket) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ticket.getEmail()));
        message.setSubject("Your Ticket Cancellation Information");
        String emailText = "Dear " + ticket.getFirstName() + ",\n\n"
                + "We are sorry to hear that you have cancelled your ticket.\n"
                + "Since you did not purchase cancellation insurance, you will be refunded %50 of the ticket price.\n"
                + "A refund of $" + String.format("%.2f", ticket.getBookedSeat().getCalculatedPrice() * 0.5) + " has been issued to your credit card.\n\n"
                + "We hope to see you another time.\n\n";
        message.setText(emailText);
        return message;
    }

}
