package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.actors;
import com.sena.crud_basic.model.movies;

public class movie_actorDTO {

    private movies movie;

    private actors actor;

    public movie_actorDTO(movies movie, actors actor) {
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
