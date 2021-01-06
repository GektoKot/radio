package com.variant.radio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class User implements Serializable {
    @Id
    @JsonView(value = Views.IdText.class)
    private String id;
    @JsonView(value = Views.IdText.class)
    private String name;
    @JsonView(value = Views.IdText.class)
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime lastVisit;


}
