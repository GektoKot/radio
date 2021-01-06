package com.variant.radio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = "id")
@Data
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = Views.Id.class)
    private Integer id;
    @JsonView(value = Views.IdText.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(value = Views.IdTextDate.class)
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(value = Views.IdTextDate.class)
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    @JsonView(value = Views.IdTextDate.class)
    private List<Comment> comments;

    @JsonView(Views.IdTextDate.class)
    private String link;
    @JsonView(Views.IdTextDate.class)
    private String linkTitle;
    @JsonView(Views.IdTextDate.class)
    private String linkDescription;
    @JsonView(Views.IdTextDate.class)
    private String linkCover;
}
