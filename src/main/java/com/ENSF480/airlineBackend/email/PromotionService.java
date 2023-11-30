package com.ENSF480.airlineBackend.email;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ENSF480.airlineBackend.reg_user.RegisteredUser;
import com.ENSF480.airlineBackend.reg_user.RegisteredUserRepository;

@Component
public class PromotionService {
    private final EmailService emailService;
    private final RegisteredUserRepository registeredUserRepository;

    private PromotionService(EmailService emailService, RegisteredUserRepository registeredUserRepository){
        this.emailService = emailService;
        this.registeredUserRepository = registeredUserRepository;
    }
    /*
     * The fields read from left to right are interpreted as follows.
     *   second
     *   minute
     *   hour
     *   day of month
     *   month
     *   days of the week
     */
    @Scheduled(cron = "0 55 * * * ?")
    public void MonthlyPromotion() throws MessagingException {
        List<RegisteredUser> users = registeredUserRepository.findAll();
        for (RegisteredUser i : users) {
           emailService.sendPromotionEmail(i);
        }
    }
}
