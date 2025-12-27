package com.example.artstore.service;

import com.example.artstore.entity.Order;
import com.example.artstore.entity.User;

import java.util.List;

public interface OrderService {

    Order placeOrder(User user);

    List<Order> getUserOrders(User user);

    Order getOrder(Long id);
    
    List<Order> getAllOrders();
    
    Order getOrderWithSecurity(Long orderId, String email);
    
    void deleteOrderById(Long orderId);
}

