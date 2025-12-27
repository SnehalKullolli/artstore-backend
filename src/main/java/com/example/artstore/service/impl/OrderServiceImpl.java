package com.example.artstore.service.impl;
import java.math.BigDecimal;
import com.example.artstore.entity.*;
import com.example.artstore.repository.*;
import com.example.artstore.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;

    public OrderServiceImpl(OrderRepository orderRepo,
                            OrderItemRepository orderItemRepo,
                            CartItemRepository cartRepo,UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        
        
    }
    
    @Override
    public Order placeOrder(User user) {

        List<CartItem> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        BigDecimal total = cartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        Order savedOrder = orderRepo.save(order);

        for (CartItem ci : cartItems) {
            OrderItem oi = new OrderItem();
            oi.setOrder(savedOrder);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getPrice());

            orderItemRepo.save(oi);
        }

        cartRepo.deleteByUser(user);

        return savedOrder;
    }
    @Override
    public Order getOrderWithSecurity(Long orderId, String email) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        User loggedInUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ADMIN → can see any order
        if ("ADMIN".equals(loggedInUser.getRole())) {
            return order;
        }

        // USER → can see only his order
        if (order.getUser().getId().equals(loggedInUser.getId())) {
            return order;
        }

        throw new RuntimeException("Access denied");
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderRepo.findByUser(user);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
    @Transactional
    public void deleteOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepo.delete(order);
    }
}

