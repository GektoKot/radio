package com.variant.radio.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = Views.IdText.class)
    private Integer id;

    @JsonView(value = Views.IdText.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(value = Views.IdTextComment.class)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonView(value = Views.IdText.class)
    private User author;


}
