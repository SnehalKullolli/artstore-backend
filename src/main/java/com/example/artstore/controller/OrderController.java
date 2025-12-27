package com.example.artstore.controller;

import com.example.artstore.entity.Order;
import com.example.artstore.entity.User;
import com.example.artstore.repository.UserRepository;
import com.example.artstore.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepo;

    public OrderController(OrderService orderService, UserRepository userRepo) {
        this.orderService = orderService;
        this.userRepo = userRepo;
    }

    
  //--------------------------------------------------
 // USER: PLACE ORDER
 // --------------------------------------------------
 @PreAuthorize("hasRole('USER')")
 @PostMapping("/place")
 public Order placeOrder(@AuthenticationPrincipal UserDetails userDetails) {
     User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
     return orderService.placeOrder(user);
 }

 // --------------------------------------------------
 // USER: SEE HIS OWN ORDERS
 // --------------------------------------------------
 @PreAuthorize("hasRole('USER')")
 @GetMapping("/my")
 public List<Order> getMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
     User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
     return orderService.getUserOrders(user);
 }

    
 // --------------------------------------------------
 // USER or ADMIN: VIEW SINGLE ORDER
 // --------------------------------------------------
 @PreAuthorize("hasAnyRole('USER','ADMIN')")
 @GetMapping("/{orderId}")
 public Order getOrder(
         @PathVariable Long orderId,
         @AuthenticationPrincipal UserDetails userDetails) {

     return orderService.getOrderWithSecurity(orderId, userDetails.getUsername());
 }
   
 // --------------------------------------------------
 // ADMIN: SEE ALL USERS ORDERS
 // --------------------------------------------------
 @PreAuthorize("hasRole('ADMIN')")
 @GetMapping("/all")
 public List<Order> getAllOrders() {
     return orderService.getAllOrders();
 }
 @PreAuthorize("hasRole('ADMIN')")
 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
     orderService.deleteOrderById(id);
     return ResponseEntity.ok("Order and its items deleted successfully");
 }
}







