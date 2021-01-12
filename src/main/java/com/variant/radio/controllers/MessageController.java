package com.variant.radio.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Message;
import com.variant.radio.domain.User;
import com.variant.radio.domain.Views;
import com.variant.radio.dto.MessagePageDto;
import com.variant.radio.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("messages")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 3;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.IdTextDate.class)
    public MessagePageDto allMessages( @AuthenticationPrincipal User user,
            @PageableDefault(size = MESSAGES_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return messageService.findForUser(pageable, user);
    }

    @GetMapping("{id}")
    @JsonView(Views.IdTextDate.class)
    public Message getOneMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message createNewMessage(@RequestBody Message message,
                                    @AuthenticationPrincipal User user) throws IOException {
        return messageService.create(message, user);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDB,
                                 @RequestBody Message message,
                                 @AuthenticationPrincipal User user) throws IOException {
        return messageService.update(messageFromDB,message, user);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageService.delete(message);
    }



}
