package com.example.artstore.controller;
import com.example.artstore.entity.Product;
import com.example.artstore.service.ProductService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam MultipartFile image
    ) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);

        Product savedProduct = productService.saveProduct(product, image);
        return ResponseEntity.ok(savedProduct);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {
        // Build a temporary Product object for text fields
        Product updatedProduct = new Product();
        updatedProduct.setName(name);
        updatedProduct.setPrice(price);
        updatedProduct.setCategory(category);
        updatedProduct.setDescription(description);

        return productService.updateProduct(id, updatedProduct, image);
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
