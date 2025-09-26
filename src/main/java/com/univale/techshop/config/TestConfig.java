package com.univale.techshop.config;

import com.univale.techshop.entities.Product;
import com.univale.techshop.entities.User;
import com.univale.techshop.repositories.ProductRepository;
import com.univale.techshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "João Gabriel do Prado Schianti", "jgpschianti07@gmail.com", "5543998664156", "123456");
        User user2 = new User(null, "Thauane Laissa de Jesus Vital", "vinhoGostoso@gmail.com", "55438872345", "1234567");
        Product p1 = new Product(null, "i5", 343.53, "url");
        productRepository.save(p1);
        userRepository.saveAll(Arrays.asList(user1, user2));

    }
}
