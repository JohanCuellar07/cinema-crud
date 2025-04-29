package com.sena.crud_basic.DTO;

import java.util.List;

import com.sena.crud_basic.model.genres;
import com.sena.crud_basic.model.actors;
import com.sena.crud_basic.model.platforms;
import com.sena.crud_basic.model.reviews;
import com.sena.crud_basic.model.movies;

public class movieDetailsDTO {
    private movies movie;
    private List<genres> genres;
    private List<actors> actors;
    private List<platforms> platforms;
    private List<reviews> reviews;

    public movieDetailsDTO(movies movie, List<genres> genres, List<actors> actors, List<platforms> platforms, List<reviews> reviews) {
        this.movie = movie;
        this.genres = genres;
        this.actors = actors;
        this.platforms = platforms;
        this.reviews = reviews;
    }

    public movies getMovie() {
        return movie;
    }

    public void setMovie(movies movie) {
        this.movie = movie;
    }

    public List<genres> getGenres() {
        return genres;
    }

    public void setGenres(List<genres> genres) {
        this.genres = genres;
    }

    public List<actors> getActors() {
        return actors;
    }

    public void setActors(List<actors> actors) {
        this.actors = actors;
    }

    public List<platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<platforms> platforms) {
        this.platforms = platforms;
    }

    public List<reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<reviews> reviews) {
        this.reviews = reviews;
    }
}
