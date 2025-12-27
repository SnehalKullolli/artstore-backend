package com.example.artstore.repository;

import com.example.artstore.entity.CartItem;
import com.example.artstore.entity.Product;
import com.example.artstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}

