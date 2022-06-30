package com.gamersdirectory.gamersapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;

    private String name;

    private String nickname;

    @ManyToOne // many accounts have one location
    @JoinColumn(name = "location_name")
    private Location location;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_interest",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private List<Interest> interests = new ArrayList<>();

}
