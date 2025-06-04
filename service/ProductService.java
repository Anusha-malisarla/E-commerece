package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> filterByCategory(String category);
    List<Product> filterByPrice(Double min, Double max);
    List<Product> searchByName(String name);
}
