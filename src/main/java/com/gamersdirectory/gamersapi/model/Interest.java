package com.gamersdirectory.gamersapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // many interest have the same one game
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private Level level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Interest interest = (Interest) o;
        return id != null && Objects.equals(id, interest.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
