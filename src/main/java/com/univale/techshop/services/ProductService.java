package com.univale.techshop.services;

import com.univale.techshop.dto.product.ProductCreateRequest;
import com.univale.techshop.entities.Category;
import com.univale.techshop.entities.Product;
import com.univale.techshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insert(ProductCreateRequest dto) {

        Category c = categoryService.findOne(dto.categoryId());

        Product p = new Product(null, dto.nome(), dto.preco(), dto.imageUrl(), c);

        return productRepository.save(p);
    }

    public Product find( Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(" Produto não encontrado"));
    }
}
