package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Recherche par nom (contient)
    List<Product> findByNameContaining(String name);

    // Produits avec stock faible (quantité <= 10)
    List<Product> findByQuantityLessThanEqual(Integer maxQuantity);
}
