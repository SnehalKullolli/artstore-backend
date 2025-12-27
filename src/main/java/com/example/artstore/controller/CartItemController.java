package com.example.artstore.controller;
import com.example.artstore.entity.CartItem;
import com.example.artstore.entity.User;
import com.example.artstore.repository.UserRepository;
import com.example.artstore.service.CartItemService;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CartItemController {

        private final CartItemService cartService;
        private final UserRepository userRepo;

        public CartItemController(CartItemService cartService, UserRepository userRepo) {
            this.cartService = cartService;
            this.userRepo = userRepo;
        }

    // -------------------------------------------------------------------
    // ADD PRODUCT TO CART
    // -------------------------------------------------------------------
    
    @PostMapping("/add")
    public CartItem addToCart(
            @RequestParam Long productId,
            @RequestParam int qty,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
        return cartService.addToCart(productId, qty, user);
    }
    // -------------------------------------------------------------------
    // GET USER CART ITEMS
    // -------------------------------------------------------------------
    
    @GetMapping
    public List<CartItem> getUserCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
        return cartService.getUserCart(user);
    }
    // -------------------------------------------------------------------
    // DELETE USER CART ITEM
    // -------------------------------------------------------------------

    @DeleteMapping("/item/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
    }
    // -------------------------------------------------------------------
    // CLEAR USER CART 
    // -------------------------------------------------------------------

    @DeleteMapping("/clear")
    public void clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
        cartService.clearCart(user);
    }
}
   
    










    

