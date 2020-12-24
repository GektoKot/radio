package com.variant.radio.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Message;
import com.variant.radio.domain.Views;
import com.variant.radio.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
        return  messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDB,
                                 @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");
        return messageRepository.save(messageFromDB);
//        return messageRepository.save(message);
//        messageFromDB.setText(message.getText());
//        return messageRepository.save(messageFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message) {

        return  messageRepository.save(message);
    }


//    private Map<String, String> getMessageFromDB(String id) {
//        return messages.stream()
//                .filter(m -> m.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(MessageNotFoundException::new);
//    }



}
