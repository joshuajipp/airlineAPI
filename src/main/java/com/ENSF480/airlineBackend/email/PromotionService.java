package com.ENSF480.airlineBackend.email;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ENSF480.airlineBackend.reg_user.RegisteredUserRepository;

@Component
public class PromotionService {
    private final EmailService emailService;
    private final RegisteredUserRepository registeredUserRepository;

    private PromotionService(EmailService emailService, RegisteredUserRepository registeredUserRepository){
        this.emailService = emailService;
        this.registeredUserRepository = registeredUserRepository;
    }
    
    @Scheduled(cron = "0 0 0 1 * ?")
    public void MonthlyPromotion() {
        
    }
}
