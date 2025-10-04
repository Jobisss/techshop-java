package com.univale.techshop.services;

import com.univale.techshop.entities.Order;
import com.univale.techshop.entities.OrderStatus;
import com.univale.techshop.entities.Payment;
import com.univale.techshop.repositories.PaymentRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderService orderService;

    public Payment payOrder(Long orderId) throws BadRequestException {

        Order order = orderService.get(orderId);
        Payment payment = new Payment(order.getTotal(), LocalDateTime.now(), order, order.getClient());
        if(order.getStatus() != OrderStatus.WAITING_PAYMENT) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pagamento ja criado.");

        if(payment.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }
        orderService.advanceOrderStatus(order.getId());
        payment.setOrder(order);

        return paymentRepository.save(payment);
    }
}
