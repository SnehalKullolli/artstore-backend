package com.example.artstore.service;

import com.example.artstore.entity.OrderItem;
import com.example.artstore.entity.User;

import java.util.List;

public interface OrderItemService {

    OrderItem saveOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemByOrderId(Long orderId);
    OrderItem getById(Long id);
    
    List<OrderItem> getOrderItemsForUser(Long orderId, User user);


    void deleteOrderItem(Long id);
}

