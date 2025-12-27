package com.example.artstore.service.impl;

import com.example.artstore.entity.Product;
import com.example.artstore.repository.ProductRepository;
import com.example.artstore.service.ProductService;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.nio.file.Path;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    
    @Override
    public Product saveProduct(Product product, MultipartFile image) throws IOException {

        
    	if (image != null) {
        // 2️⃣ Generate unique file name
        //String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String fileName =
        	    UUID.randomUUID().toString() +
        	    image.getOriginalFilename().substring(
        	        image.getOriginalFilename().lastIndexOf(".")
        	    );


        // 3️⃣ Save image to local file system
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 4️⃣ Store only URL in DB
        product.setImageUrl("/images/" + fileName);

    	}// 5️⃣ Save product data
        return repo.save(product);
    }
    @Override
    public Product updateProduct(Long id, Product updatedProduct,MultipartFile image)throws IOException {

        Product existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setDescription(updatedProduct.getDescription());

     // Update image only if a new file is uploaded
        if (image != null && !image.isEmpty()) {   
        	 // 1️⃣ Delete old image
            if (existing.getImageUrl() != null) {
                String oldPath = existing.getImageUrl().replace("/images/", "");
                File oldFile = new File(uploadDir + "/" + oldPath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // 2️⃣ Save new image
            
            //String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            String fileName =
            	    UUID.randomUUID().toString() +
            	    image.getOriginalFilename().substring(
            	        image.getOriginalFilename().lastIndexOf(".")
            	    );


         // 3️⃣ Save image to local file system
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 3️⃣ Update DB
            existing.setImageUrl("/images/" + fileName);
        }
        return repo.save(existing);
    }
    


    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    
    @Override
    public void deleteProduct(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Delete image from server
        if (product.getImageUrl() != null) {
            String imagePath = product.getImageUrl().replace("/images/", "uploads/products/");
            File file = new File(imagePath);
            if (file.exists()) {
                file.delete();
            }
        }

        // Delete the product from DB
        repo.deleteById(id);
    }

}

