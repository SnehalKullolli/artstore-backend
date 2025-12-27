package com.example.artstore.service;

import com.example.artstore.entity.CartItem;
import com.example.artstore.entity.User;
import java.util.List;

public interface CartItemService {

    CartItem addToCart(Long productId, int qty, User user);

    List<CartItem> getUserCart(User user);

    void removeItem(Long cartItemId);

    void clearCart(User user);
}
