package com.variant.radio.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.variant.radio.domain.Message;
import com.variant.radio.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.IdTextDate.class)
public class MessagePageDto {

    private List<Message> messages;
    private int currentPage;
    private int totalPages;
}
