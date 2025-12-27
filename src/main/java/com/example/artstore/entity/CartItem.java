package com.example.artstore.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Min(1)
    private int quantity;

    // RELATION: Many cart items -> 1 Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;           // cart belongs to one user
    
    @Column(precision = 12, scale = 2)
    private BigDecimal price;

    // constructors

    public CartItem() {}

    public CartItem(int quantity,BigDecimal price, Product product,User user) {
        this.quantity = quantity;
        this.product = product;
        this.price=price;
        this.user=user;
    }

    // getters & setters

    public Long getId() { return id; }
    

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
