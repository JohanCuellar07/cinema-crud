package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie_director")
public class movie_director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private directors director;

    public movie_director(){}

    public movie_director(int id, movies movie, directors director) {
        this.id = id;
        this.movie = movie;
        this.director = director;
    }

    public movies getMovie_id() {
        return movie;
    }

    public void setMovie_id(movies movie) {
        this.movie = movie;
    }

    public directors getDirector_id() {
        return director;
    }

    public void setDirector_id(directors director) {
        this.director = director;
    }
}
