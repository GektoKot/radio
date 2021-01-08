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
//@Data
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {
    @Id
    @JsonView(value = Views.IdText.class)
    private String id;
    @JsonView(value = Views.IdText.class)
    private String name;
    @JsonView(value = Views.IdText.class)
    private String userpic;
    private String email;
    @JsonView(value = Views.IdTextProfile.class)
    private String gender;
    @JsonView(value = Views.IdTextProfile.class)
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(value = Views.IdTextProfile.class)
    private LocalDateTime lastVisit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonView(value = Views.IdTextProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<User> subscriptions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonView(value = Views.IdTextProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<User> subscribers = new HashSet<>();


}
