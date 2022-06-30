package com.games.directory.gamersapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    @ManyToOne // many users have one location
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    @ToString.Exclude
    private List<Interest> interests = new ArrayList<>();

}
