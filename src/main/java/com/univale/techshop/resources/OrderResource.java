package com.univale.techshop.resources;

import com.univale.techshop.dto.order.OrderCreateRequest;
import com.univale.techshop.entities.Order;
import com.univale.techshop.repositories.OrderRepository;
import com.univale.techshop.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/orders")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> insert(@Valid @RequestBody OrderCreateRequest body) {
        Order created = orderService.create(body);
        return ResponseEntity.ok().body(created);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> listByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
}
