package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final int LOW_STOCK_THRESHOLD = 10;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Product> findById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID non valide");
        }
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Transactional
    @Override
    public List<Product> findLowStockProducts() {
        return productRepository.findByQuantityLessThanEqual(LOW_STOCK_THRESHOLD);
    }

    @Transactional
    @Override
    public Product addStock(Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produit non valide : " + productId));

        int newQuantity = product.getQuantity() + quantity;
        product.setQuantity(newQuantity);

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product removeStock(Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("La quantité à retirer doit être positive");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé : " + productId));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException(
                    "Stock insuffisant. Disponible : " + product.getQuantity() +
                            ", demandé : " + quantity
            );
        }

        int newQuantity = product.getQuantity() - quantity;
        product.setQuantity(newQuantity);

        return productRepository.save(product);
    }
}
