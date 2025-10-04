package com.univale.techshop.resources;

import com.univale.techshop.dto.product.ProductCreateRequest;
import com.univale.techshop.entities.Product;
import com.univale.techshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.univale.techshop.repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping( value = "/products")
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody ProductCreateRequest product) {
        try {
            Product saved = productService.insert(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(" Erro : " + e.getMessage());
        }
    }
}
