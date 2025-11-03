package com.example.demo.services;

import com.example.demo.entities.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long id);
    List<Product> findByNameContaining(String name);
    List<Product> findLowStockProducts();
    Product addStock(Long productId, Integer quantity);
    Product removeStock(Long productId, Integer quantity);
}
