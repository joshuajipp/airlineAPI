package com.ENSF480.airlineBackend.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    
}
