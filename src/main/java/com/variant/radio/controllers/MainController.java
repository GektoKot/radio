package com.variant.radio.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.variant.radio.domain.User;
import com.variant.radio.domain.Views;
import com.variant.radio.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;
//переменная окружения
    @Value("${spring.profiles.active}")
    private String profile;
    private final ObjectWriter objectWriter;

    @Autowired
    public MainController(MessageRepository messageRepository,  ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;

        this.objectWriter = objectMapper
                .setConfig(objectMapper.getSerializationConfig())
                .writerWithView(Views.IdTextDate.class);
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {

        Map<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            String messages = objectWriter.writeValueAsString(messageRepository.findAll());
            model.addAttribute("messages", messages);
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
