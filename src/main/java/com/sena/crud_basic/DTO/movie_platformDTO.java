package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.movies;
import com.sena.crud_basic.model.platforms;

public class movie_platformDTO {

    private movies movie;

    private platforms platform;

    public movie_platformDTO(movies movie, platforms platform) {
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
