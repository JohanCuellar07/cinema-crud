package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.movie_actorDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.movie_actor;
import com.sena.crud_basic.repository.Imovie_actor;

@Service
public class movie_actorService {
    @Autowired
    private Imovie_actor data;

    public List<movie_actor> findAll() {
        return data.findAll();
    }

    public Optional<movie_actor> findById(int id) {
        return data.findById(id);
    }

    public responseDTO save(movie_actorDTO movie_actorDTO) {
        if (movie_actorDTO.getMovie_id() == null || movie_actorDTO.getActor_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or actor_id cannot be null",
                "Bad Request"
            );
            return respuesta;
        }
        movie_actor movie_actorRegister = converToModel(movie_actorDTO);
        data.save(movie_actorRegister);
        responseDTO respuesta = new responseDTO(
            "Was succesfully saved",
            "OK"
        );
        return respuesta;
    }

    public responseDTO updateMovie_Actor(int id, movie_actorDTO dto) {
        if (dto.getMovie_id() == null || dto.getActor_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or actor_id cannot be null",
                "Bad Request"
            );
            return respuesta;
        }
        movie_actor existingMovieActor = data.findById(id).get();
        existingMovieActor.setMovie_id(dto.getMovie_id());
        existingMovieActor.setActor_id(dto.getActor_id());
        data.save(existingMovieActor);
        responseDTO respuesta = new responseDTO(
            "Was successfully updated",
            "OK"
        );
        return respuesta;
    }

    public movie_actorDTO converToDTO(movie_actor movie_actor) {
        movie_actorDTO movie_actorDTO = new movie_actorDTO(
            movie_actor.getMovie_id(),
            movie_actor.getActor_id()
        );
        return movie_actorDTO;
    }

    public movie_actor converToModel(movie_actorDTO movie_actorDTO) {
        movie_actor movie_actor = new movie_actor(
            0,
            movie_actorDTO.getMovie_id(),
            movie_actorDTO.getActor_id()
        );
        return movie_actor;
    }
}
