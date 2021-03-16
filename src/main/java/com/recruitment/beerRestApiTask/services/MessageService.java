package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.Message;
import com.recruitment.beerRestApiTask.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository MessageRepository) {
        this.messageRepository = MessageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(Message Message) {
        return messageRepository.save(Message);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(Long.valueOf(id));
    }
}
