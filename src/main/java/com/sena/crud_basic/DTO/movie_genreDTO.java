package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.genres;
import com.sena.crud_basic.model.movies;

public class movie_genreDTO {

    private movies movie;

    private genres genre;

    public movie_genreDTO(movies movie, genres genre) {
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
