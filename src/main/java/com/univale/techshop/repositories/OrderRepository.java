package com.univale.techshop.repositories;

import com.univale.techshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByClientId(Long id);
}
