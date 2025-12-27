package com.example.artstore.service.impl;


import com.example.artstore.entity.Order;
import com.example.artstore.entity.OrderItem;
import com.example.artstore.entity.User;
import com.example.artstore.repository.OrderItemRepository;
import com.example.artstore.repository.OrderRepository;
import com.example.artstore.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepo;

    public OrderItemServiceImpl(OrderRepository orderRepo,OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepo = orderRepo;
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
    @Override
    public List<OrderItem> getOrderItemsForUser(Long orderId, User user) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        return orderItemRepository.findByOrderId(orderId);
    }
}

