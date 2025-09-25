package com.univale.techshop.services;

import com.univale.techshop.entities.Product;
import com.univale.techshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insertOne( Product product) {
        return productRepository.save(product);
    }
}
