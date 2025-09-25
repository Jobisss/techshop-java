package com.univale.techshop.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table( name = "orders")
public class Order  implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany( mappedBy = "order")
    private List<Product> products;

    @ManyToOne
    @JoinColumn( name = "client_id")
    private User client;
}
