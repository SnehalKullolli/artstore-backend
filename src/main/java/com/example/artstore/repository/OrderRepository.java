package com.example.artstore.repository;

import com.example.artstore.entity.Order;
import com.example.artstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}

