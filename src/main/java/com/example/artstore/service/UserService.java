package com.example.artstore.service;

import com.example.artstore.entity.User;

public interface UserService {

    User registerUser(User user);

    User login(String email, String password);

    User getById(Long id);

    boolean existsByEmail(String email);
}
