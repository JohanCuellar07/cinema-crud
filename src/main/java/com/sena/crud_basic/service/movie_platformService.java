
package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.movie_platformDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.movie_platform;
import com.sena.crud_basic.repository.Imovie_platform;

@Service
public class movie_platformService {
    @Autowired
    private Imovie_platform data;

    public List<movie_platform> findAll() {
        return data.findAll();
    }

    public Optional<movie_platform> findById(int id) {
        return data.findById(id);
    }

    public responseDTO save(movie_platformDTO movie_platformDTO) {
        if (movie_platformDTO.getMovie_id() == null || movie_platformDTO.getPlatform_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or platform_id cannot be null",
                "Bad Request"
            );
            return respuesta;
        }
        movie_platform movie_platformRegister = converToModel(movie_platformDTO);
        data.save(movie_platformRegister);
        responseDTO respuesta = new responseDTO(
            "Was succesfully saved",
            "OK"
        );
        return respuesta;
    }

    public responseDTO updateMovie_Platform(int id, movie_platformDTO dto) {
        if (dto.getMovie_id() == null || dto.getPlatform_id() == null) {
            responseDTO respuesta = new responseDTO(
                "The movie_id or platform_id cannot be null",
                "Bad Request"
            );
            return respuesta;
        }
        movie_platform existingMoviePlatform = data.findById(id).get();
        existingMoviePlatform.setMovie_id(dto.getMovie_id());
        existingMoviePlatform.setPlatform_id(dto.getPlatform_id());
        data.save(existingMoviePlatform);
        responseDTO respuesta = new responseDTO(
            "Was successfully updated",
            "OK"
        );
        return respuesta;
    }

    public movie_platformDTO converToDTO(movie_platform movie_platform) {
        movie_platformDTO movie_platformDTO = new movie_platformDTO(
            movie_platform.getMovie_id(),
            movie_platform.getPlatform_id()
        );
        return movie_platformDTO;
    }

    public movie_platform converToModel(movie_platformDTO movie_platformDTO) {
        movie_platform movie_platform = new movie_platform(
            0,
            movie_platformDTO.getMovie_id(),
            movie_platformDTO.getPlatform_id()
        );
        return movie_platform;
    }
}
