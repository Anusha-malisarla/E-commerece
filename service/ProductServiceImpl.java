package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository repo;

    public Product addProduct(Product p) { return repo.save(p); }

    public Product updateProduct(Long id, Product p) {
        Product existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setCategory(p.getCategory());
        existing.setStockQuantity(p.getStockQuantity());
        return repo.save(existing);
    }

    public void deleteProduct(Long id) { repo.deleteById(id); }

    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public List<Product> getAllProducts() { return repo.findAll(); }

    public List<Product> filterByCategory(String category) { return repo.findByCategory(category); }

    public List<Product> filterByPrice(Double min, Double max) { return repo.findByPriceBetween(min, max); }

    public List<Product> searchByName(String name) { return repo.findByNameContainingIgnoreCase(name); }
}
