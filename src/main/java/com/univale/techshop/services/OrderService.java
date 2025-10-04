package com.univale.techshop.services;

import com.univale.techshop.entities.Order;
import com.univale.techshop.entities.OrderItem;
import com.univale.techshop.dto.order.OrderCreateRequest;
import com.univale.techshop.entities.OrderStatus;
import com.univale.techshop.repositories.OrderItemRepository;
import com.univale.techshop.repositories.OrderRepository;
import com.univale.techshop.repositories.ProductRepository;
import com.univale.techshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private final Map<OrderStatus, OrderStatus> OrderAdvanceOrderMap = new EnumMap(OrderStatus.class);

    OrderService() {
        OrderAdvanceOrderMap.put(OrderStatus.WAITING_PAYMENT, OrderStatus.PAID);
        OrderAdvanceOrderMap.put(OrderStatus.PAID, OrderStatus.SHIPPED);
        OrderAdvanceOrderMap.put(OrderStatus.SHIPPED, OrderStatus.DELIVERED);
    }
    @Transactional
    public Order create( OrderCreateRequest dto ) {

        var client = userRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"));

        var order = new Order();
        order.setClient(client);
        var items = dto.items().stream().map( item -> {
            OrderItem oi = new OrderItem();
            var product = productService.find(item.productId());
            oi.setPrice(product.getPreco());
            oi.setOrder(order);
            oi.setQuantity(item.quantity());
            oi.setTotalPrice(product.getPreco().multiply(BigDecimal.valueOf(oi.getQuantity()) ));
            return oi;
        }).toList();


        order.setOrderItem(items);
        var total = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
        orderRepository.save(order);
        return order;
    }

    public Order get( long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public List<Order> getUserOrders(long clientId ) {
        return orderRepository.findByClientId(clientId);
    }

    public Order advanceOrderStatus(Long orderId) throws BadRequestException {
        Order order = orderRepository.findById(orderId).orElseThrow();

        var current = order.getStatus();
        var next = OrderAdvanceOrderMap.get(current);

        if(next == null) {
            throw new BadRequestException("order cannot advance");
        }

        order.setStatus(next);
        return orderRepository.save(order);
    }
}
