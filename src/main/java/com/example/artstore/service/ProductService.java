package com.example.artstore.service;

import com.example.artstore.entity.Product;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    Product saveProduct(Product product, MultipartFile image) throws IOException;
    
    Product updateProduct(
    	    Long id,
    	    Product updatedProduct,
    	    MultipartFile image
    	) throws IOException;

    List<Product> getAllProducts();

    Product getProduct(Long id);

    List<Product> getByCategory(String category);

    void deleteProduct(Long id);
}
