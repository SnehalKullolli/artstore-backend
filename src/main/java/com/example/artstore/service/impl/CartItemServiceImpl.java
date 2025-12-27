package com.example.artstore.service.impl;

import com.example.artstore.entity.CartItem;
import com.example.artstore.entity.Product;
import com.example.artstore.entity.User;
import com.example.artstore.repository.CartItemRepository;
import com.example.artstore.repository.ProductRepository;
import com.example.artstore.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(CartItemRepository cartRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Override
    public CartItem addToCart(Long productId, int qty, User user) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartRepo.findByUserAndProduct(user, product)
                .orElse(new CartItem());
        item.setProduct(product);
        item.setQuantity(item.getQuantity() + qty);
        item.setUser(user);
        item.setPrice(product.getPrice());

        return cartRepo.save(item);
    }
    

        

       
        
       
        
     
    @Override
    public List<CartItem> getUserCart(User user) {
        return cartRepo.findByUser(user);
    }

    @Override
    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public void clearCart(User user) {
        cartRepo.deleteByUser(user);
    }
}

