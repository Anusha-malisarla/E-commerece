package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ProductService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping public ResponseEntity<Product> add(@RequestBody Product p) {
        return new ResponseEntity<>(service.addProduct(p), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
        return ResponseEntity.ok(service.updateProduct(id, p));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping public ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}") public ResponseEntity<Product> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice) {
        if (category != null) return ResponseEntity.ok(service.filterByCategory(category));
        if (minPrice != null && maxPrice != null) return ResponseEntity.ok(service.filterByPrice(minPrice, maxPrice));
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search") public ResponseEntity<List<Product>> search(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
}
