package com.univale.techshop.services;

import com.univale.techshop.entities.Payment;
import com.univale.techshop.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment create(Payment payment) {

        if(payment.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }

        return paymentRepository.save(payment);
    }
}
