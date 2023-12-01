package com.ENSF480.airlineBackend.payment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/creditCard")
public class CreditCardController {
    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService){
        this.creditCardService = creditCardService;
    }

    @PostMapping
    private void createCreditCard(@RequestBody CreditCard creditCard){
        creditCardService.save(creditCard);
    }
}
