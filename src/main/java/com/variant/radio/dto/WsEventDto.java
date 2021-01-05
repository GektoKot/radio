package com.variant.radio.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Views;

import lombok.*;

@JsonView(Views.Id.class)
@Data
@AllArgsConstructor
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;

    /*public WsEventDto(ObjectType objectType, EventType eventType, String body) {
        this.objectType = objectType;
        this.eventType = eventType;
        this.body = body;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }*/
}
