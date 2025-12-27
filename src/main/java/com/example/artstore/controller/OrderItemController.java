package com.example.artstore.controller;

import com.example.artstore.entity.OrderItem;
import com.example.artstore.entity.User;
import com.example.artstore.repository.UserRepository;
import com.example.artstore.service.OrderItemService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final UserRepository userRepo;

    public OrderItemController(OrderItemService orderItemService,UserRepository userRepo) {
        this.orderItemService = orderItemService;
        this.userRepo = userRepo;
    }

    // -------------------------------------------------------
    // GET ALL ORDER ITEMS FOR A SPECIFIC ORDER
    // -------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/order/{orderId}")
    public List<OrderItem> getOrderItemsAdmin(@PathVariable Long orderId) {
        return orderItemService.getOrderItemByOrderId(orderId);
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getMyOrderItems(
            @PathVariable Long orderId,
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
        return orderItemService.getOrderItemsForUser(orderId, user);
    }
    
        
    // -------------------------------------------------------
    // GET SINGLE ORDER ITEM BY ID
    // -------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public OrderItem getOrderItem(@PathVariable Long id) {
        return orderItemService.getById(id);
    }
    
    // -------------------------------------------------------
    // DELETE ORDER ITEM (ADMIN USE)
    // -------------------------------------------------------
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
