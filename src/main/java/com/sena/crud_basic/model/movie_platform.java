package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie_platform")
public class movie_platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private platforms platform;

    public movie_platform(){}

    public movie_platform(int id, movies movie, platforms platform) {
        this.id = id;
        this.movie = movie;
        this.platform = platform;
    }

    public movies getMovie_id() {
        return movie;
    }

    public void setMovie_id(movies movie) {
        this.movie = movie;
    }

    public platforms getPlatform_id() {
        return platform;
    }

    public void setPlatform_id(platforms platform) {
        this.platform = platform;
    }
}
