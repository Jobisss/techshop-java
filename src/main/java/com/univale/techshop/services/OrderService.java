package com.univale.techshop.services;

import com.univale.techshop.entities.Order;
import com.univale.techshop.entities.OrderItem;
import com.univale.techshop.dto.order.OrderCreateRequest;
import com.univale.techshop.entities.OrderStatus;
import com.univale.techshop.repositories.OrderItemRepository;
import com.univale.techshop.repositories.OrderRepository;
import com.univale.techshop.repositories.ProductRepository;
import com.univale.techshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public Order create( OrderCreateRequest dto ) {

        var client = userRepository.findById(dto.clientId()).orElseThrow(() -> new IllegalArgumentException("Client not found"));

        var order = new Order();
        order.setClient(client);
        var items = dto.items().stream().map( item -> {
            OrderItem orderItem = new OrderItem();
            var product = productService.find(item.productId());
            orderItem.setPrice(product.getPreco());
            orderItem.setOrder(order);
            orderItem.setQuantity(item.quantity());
            orderItem.setTotalPrice(BigDecimal.valueOf(orderItem.getPrice() * orderItem.getQuantity()));
            return orderItem;
        }).toList();


        order.setOrderItem(items);
        orderRepository.save(order);
        orderItemRepository.saveAll(items);
        return order;
    }

    public Order get( long orderId) {
        return orderRepository.getReferenceById(orderId);
    }

    public List<Order> getUserOrders(long clientId ) {
        return orderRepository.findByClientId(clientId);
    }

}
