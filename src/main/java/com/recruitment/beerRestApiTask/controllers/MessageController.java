package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.Message;
import com.recruitment.beerRestApiTask.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type"})
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(this.messageService.getAllMessages());
    }

    @PostMapping
    @ResponseBody
    public Message saveMessage(@RequestBody Message message) {
        return this.messageService.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable String id) {
        this.messageService.deleteMessage(id);
    }
}


