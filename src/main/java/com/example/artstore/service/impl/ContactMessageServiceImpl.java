package com.example.artstore.service.impl;



import org.springframework.stereotype.Service;
import com.example.artstore.entity.ContactMessage;
import com.example.artstore.repository.ContactMessageRepository;
import com.example.artstore.service.ContactMessageService;
import java.util.List;
@Service
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository repository;

    public ContactMessageServiceImpl(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public ContactMessage saveMessage(ContactMessage message) {
        return repository.save(message);
    }
    

    @Override
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }


    @Override
    public ContactMessage get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }
}

