package com.example.artstore.entity;
 

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(
            regexp = ".*[a-zA-Z].*",
            message = "Product name cannot be only numbers"
        )
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String imageUrl;
    
    @Column(nullable = false,precision = 12, scale = 2)
    private BigDecimal price;
    
    @NotBlank(message = "Category cannot be empty")
    @Pattern(
            regexp = ".*[a-zA-Z].*",
            message = "Product name cannot be only numbers"
        )
    private String category;
    private String description;

    // ----- Getters & Setters -----

    public Long getId() {
        return id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
    	return description;
    }
    public void setDescription(String description) {
    	this.description = description; }
}

