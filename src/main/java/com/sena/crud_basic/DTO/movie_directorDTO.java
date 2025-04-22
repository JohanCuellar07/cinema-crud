package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.directors;
import com.sena.crud_basic.model.movies;

public class movie_directorDTO {

    private movies movie;

    private directors director;

    public movie_directorDTO(movies movie, directors director) {
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
