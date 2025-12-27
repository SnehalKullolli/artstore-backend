package com.example.artstore.service;



import com.example.artstore.entity.ContactMessage;
import java.util.List;
public interface ContactMessageService {
    ContactMessage saveMessage(ContactMessage message);
    List<ContactMessage> getAllMessages();

    ContactMessage get(Long id);
}
