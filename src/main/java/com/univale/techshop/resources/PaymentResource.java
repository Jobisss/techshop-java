    package com.univale.techshop.resources;

    import com.univale.techshop.entities.Order;
    import com.univale.techshop.entities.OrderStatus;
    import com.univale.techshop.services.OrderService;
    import com.univale.techshop.services.PaymentService;
    import org.apache.coyote.BadRequestException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping( value = "/payment")
    public class PaymentResource {

        @Autowired
        private PaymentService paymentService;

        @PostMapping("/{orderId}")
        public ResponseEntity<?> pay(@PathVariable Long orderId) throws BadRequestException {
            paymentService.payOrder(orderId);

            return ResponseEntity.ok().body("Payed");
        }
    }
