package com.variant.radio.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Message;
import com.variant.radio.domain.Views;
import com.variant.radio.dto.EventType;
import com.variant.radio.dto.ObjectType;
import com.variant.radio.repository.MessageRepository;
import com.variant.radio.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageRepository messageRepository;

    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdText.class);
    }

    @GetMapping
    @JsonView(Views.IdText.class)
    public List<Message> allMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    public Message getOneMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message addNewMessage(@RequestBody Message message) {
        message.setDateOfCreation(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        wsSender.accept(EventType.CREATE, savedMessage);
        return savedMessage;
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDB,
                                 @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");
        Message updatedMessage = messageRepository.save(messageFromDB);
        wsSender.accept(EventType.UPDATE, updatedMessage);
        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageRepository.delete(message);
        wsSender.accept(EventType.DELETE, message);
    }

    /*@MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message) {

        return  messageRepository.save(message);
    }*/


/*    private Map<String, String> getMessageFromDB(String id) {
        return messages.stream()
                .filter(m -> m.get("id").equals(id))
                .findFirst()
                .orElseThrow(MessageNotFoundException::new);
    }*/



}
