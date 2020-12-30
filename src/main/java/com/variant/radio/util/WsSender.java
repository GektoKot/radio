package com.variant.radio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.variant.radio.dto.EventType;
import com.variant.radio.dto.ObjectType;
import com.variant.radio.dto.WsEventDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WsSender {

    private final SimpMessagingTemplate smt;
    private final ObjectMapper objectMapper;

    public WsSender(SimpMessagingTemplate smt, ObjectMapper objectMapper) {
        this.smt = smt;
        this.objectMapper = objectMapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter objectWriter = objectMapper
                .setConfig(objectMapper.getSerializationConfig())
                .writerWithView(view);
        return (EventType eventType, T payload) -> {
            String stringPayload;
            try {
                stringPayload = objectWriter.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            smt.convertAndSend("/topic/activity", new WsEventDto(objectType, eventType, stringPayload));
        };

    }
}
