package com.univale.techshop.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class OrderItem implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn( name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn( name = "product_id", nullable = true)
    private Product product;

    public OrderItem() {}

    public OrderItem(Long id, int quantity, double price, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
