package com.example.demo;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication1 implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        productRepository.save(new Product(null, "computer", 1250.0, 4));
//        productRepository.save(new Product(null, "book", 150.0, 40));
//        productRepository.save(new Product(null, "pen", 1.5, 100));
//        productRepository.save(new Product(null, "mouse", 75.5, 25));
    }
}
