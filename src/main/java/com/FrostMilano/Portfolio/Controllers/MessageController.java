package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.MessageDto;
import com.FrostMilano.Portfolio.dtos.MessageRequestDto;
import com.FrostMilano.Portfolio.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageRequestDto request) {
        // Validate request, authenticate user, etc.
        MessageDto message = messageService.sendMessage(request);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/{userId}/messages")
    public ResponseEntity<List<MessageDto>> getMessage(@PathVariable Long userId) {
        // Validate request, authenticate user, etc.
        List<MessageDto> message = messageService.getAllMessages(userId);
        return ResponseEntity.ok(message);
    }

    // Other endpoints for retrieving messages, etc.
}