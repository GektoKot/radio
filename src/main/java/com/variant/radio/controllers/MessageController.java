package com.variant.radio.controllers;

import com.variant.radio.exceptions.MessageNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("messages")
public class MessageController {

    private static int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping
    public List<Map<String, String>> allMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOneMessage(@PathVariable String id) {
        return getMessageFromDB(id);
    }

    @PostMapping
    public Map<String, String> addNewMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);


        return  message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDB = getMessageFromDB(id);

        messageFromDB.putAll(message);
        messageFromDB.put("id", id);

        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {
        messages.remove(getMessageFromDB(id));
    }

    private Map<String, String> getMessageFromDB(String id) {
        return messages.stream()
                .filter(m -> m.get("id").equals(id))
                .findFirst()
                .orElseThrow(MessageNotFoundException::new);
    }

}
