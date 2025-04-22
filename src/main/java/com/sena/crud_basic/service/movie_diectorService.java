package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.movie_directorDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.movie_director;
import com.sena.crud_basic.repository.Imovie_director;

@Service
public class movie_diectorService {
    @Autowired
    private Imovie_director data;

    public List<movie_director> findAll() {
        return data.findAll();
    }

    public Optional<movie_director> findById(int id) {
        return data.findById(id);
    }

    public responseDTO save(movie_directorDTO movie_directorDTO) {
        if (movie_directorDTO.getMovie_id() == null || movie_directorDTO.getDirector_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or director_id cannot be null",
                HttpStatus.BAD_REQUEST.toString()
            );
            return respuesta;
        }
        movie_director movie_directorRegister = converToModel(movie_directorDTO);
        data.save(movie_directorRegister);
        responseDTO respuesta = new responseDTO(
            "Was succesfully saved",
            HttpStatus.OK.toString()
        );
        return respuesta;
    }

    public responseDTO updateMovie_Director(int id, movie_directorDTO dto) {
        if (dto.getMovie_id() == null || dto.getDirector_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or director_id cannot be null",
                HttpStatus.BAD_REQUEST.toString()
            );
            return respuesta;
        }
        movie_director existingMovieDirector = data.findById(id).get();
        existingMovieDirector.setMovie_id(dto.getMovie_id());
        existingMovieDirector.setDirector_id(dto.getDirector_id());
        data.save(existingMovieDirector);
        responseDTO respuesta = new responseDTO(
            "Was successfully updated",
            HttpStatus.OK.toString()
        );
        return respuesta;
    }

    public movie_directorDTO converToDTO(movie_director movie_director) {
        movie_directorDTO movie_directorDTO = new movie_directorDTO(
            movie_director.getMovie_id(),
            movie_director.getDirector_id()
        );
        return movie_directorDTO;
    }

    public movie_director converToModel(movie_directorDTO movie_directorDTO) {
        movie_director movie_director = new movie_director(
            0,
            movie_directorDTO.getMovie_id(),
            movie_directorDTO.getDirector_id()
        );
        return movie_director;
    }
}
