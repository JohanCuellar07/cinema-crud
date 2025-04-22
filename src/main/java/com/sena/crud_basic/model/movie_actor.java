package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie_actor")
public class movie_actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private actors actor;

    public movie_actor(){}

    public movie_actor(int id, movies movie, actors actor) {
        this.id = id;
        this.movie = movie;
        this.actor = actor;
    }

    public movies getMovie_id() {
        return movie;
    }

    public void setMovie_id(movies movie) {
        this.movie = movie;
    }

    public actors getActor_id() {
        return actor;
    }

    public void setActor_id(actors actor) {
        this.actor = actor;
    }
}
