package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.movie_genreDTO;
import com.sena.crud_basic.model.movie_genre;
import com.sena.crud_basic.repository.Imovie_genre;

@Service
public class movie_genreService {
    @Autowired
    private Imovie_genre data;

    public void save(movie_genreDTO movie_genreDTO){
        movie_genre movie_genreRegister = converToModel(movie_genreDTO);
        data.save(movie_genreRegister);
    }

    public movie_genreDTO converToDTO(movie_genre movie_genre){
        movie_genreDTO movie_genreDTO = new movie_genreDTO(
            movie_genre.getMovie_id(),
            movie_genre.getGenre_id()
        );
        return movie_genreDTO;
    }

    public movie_genre converToModel(movie_genreDTO movie_genreDTO){
        movie_genre movie_genre = new movie_genre(
            0,
            movie_genreDTO.getMovie_id(),
            movie_genreDTO.getGenre_id()
        );
        return movie_genre;
    }
}
