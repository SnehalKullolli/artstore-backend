package com.example.artstore.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.artstore.entity.ContactMessage;
import com.example.artstore.service.ContactMessageService;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
 // allow frontend to call API
@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    private final ContactMessageService service;

    public ContactMessageController(ContactMessageService service) {
        this.service = service;
    }
    
    @PostMapping("/submit")
    public ContactMessage submitMessage(@RequestBody ContactMessage message) {
        return service.saveMessage(message);
    }
    // Admin dashboard fetch all messages
    // -------------------- FETCH ALL MESSAGES --------------------
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return service.getAllMessages();
    }

}


