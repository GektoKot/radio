package com.variant.radio.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
@JsonView(Views.IdText.class)
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;

    public WsEventDto(ObjectType objectType, EventType eventType, String body) {
        this.objectType = objectType;
        this.eventType = eventType;
        this.body = body;
    }
}
