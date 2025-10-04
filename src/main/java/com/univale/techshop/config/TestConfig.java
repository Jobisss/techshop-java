package com.univale.techshop.config;

import com.univale.techshop.entities.Category;
import com.univale.techshop.entities.Product;
import com.univale.techshop.entities.User;
import com.univale.techshop.repositories.CategoryRepository;
import com.univale.techshop.repositories.ProductRepository;
import com.univale.techshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "João Gabriel do Prado Schianti", "jgpschianti07@gmail.com", "5543998664156", "123456");
        User user2 = new User(null, "Thauane Laissa de Jesus Vital", "vinhoGostoso@gmail.com", "55438872345", "1234567");
        Category c1 = new Category(null, "Eletronicos");
        Product p1 = new Product(null, "i5", new BigDecimal("235.5"), "url", c1);
        Product p2 = new Product(null, "i3", new BigDecimal("153.5"), "url2", c1);
        Product p3 = new Product(null, "i7", new BigDecimal("355.5"), "url3", c1);
        categoryRepository.save(c1);
        productRepository.saveAll(Arrays.asList(p1,p2,p3));
        userRepository.saveAll(Arrays.asList(user1, user2));

    }
}
