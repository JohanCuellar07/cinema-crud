package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie_genre")
public class movie_genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private genres genre;

    public movie_genre(movies movie, genres genre) {
        this.movie = movie;
        this.genre = genre;
    }

    public movies getMovie_id() {
        return movie;
    }

    public void setMovie_id(movies movie) {
        this.movie = movie;
    }

    public genres getGenre_id() {
        return genre;
    }

    public void setGenre_id(genres genre) {
        this.genre = genre;
    }
}
