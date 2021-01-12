package com.variant.radio.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
public class User implements Serializable {
    @Id
    @JsonView(value = Views.IdText.class)
    private String id;
    @JsonView(value = Views.IdText.class)
    private String name;
    @JsonView(value = Views.IdText.class)
    private String userpic;
    @JsonView(value = Views.IdTextProfile.class)
    private String email;
    @JsonView(value = Views.IdTextProfile.class)
    private String gender;
    @JsonView(value = Views.IdTextProfile.class)
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(value = Views.IdTextProfile.class)
    private LocalDateTime lastVisit;

    @JsonView(value = Views.IdTextProfile.class)
    @OneToMany(
            mappedBy = "subscriber",
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<UserSubscription> subscriptions = new HashSet<>();

    @JsonView(value = Views.IdTextProfile.class)
    @OneToMany(
            mappedBy = "channel",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<UserSubscription> subscribers = new HashSet<>();


}
