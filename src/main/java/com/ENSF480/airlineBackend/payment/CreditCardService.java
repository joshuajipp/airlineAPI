package com.ENSF480.airlineBackend.payment;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository){
        this.creditCardRepository = creditCardRepository;
    }

    public void save(CreditCard creditCard){
        Optional<CreditCard> data = creditCardRepository.findById(creditCard.getCardNumber());
        if (data.isPresent()){
            throw new RuntimeException("Credit Card already exists");
        }
        creditCardRepository.save(creditCard);
    }
}
