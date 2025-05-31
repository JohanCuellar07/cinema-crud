
package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public List<movie_platform> findAllByPlatformId(int platform_id) {
        return data.findAllByPlatformId(platform_id);
    }

    public responseDTO deleteByMovieId(int movie_id) {
        List<movie_platform> moviePlatforms = data.findAllByMovieId(movie_id);
        
        if (moviePlatforms.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "No platforms found for the given movie"
            );
            return respuesta;
        }
    
        data.deleteByMovieId(movie_id);
    
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "All platform relations for the movie were deleted successfully"
        );
        return respuesta;
    }

    public responseDTO deleteByPlatformId(int platform_id) {
        List<movie_platform> moviePlatforms = data.findAllByPlatformId(platform_id);
        
        if (moviePlatforms.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "No movies found for the given platform"
            );
            return respuesta;
        }
    
        data.deleteByPlatformId(platform_id);
    
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "All movie relations for the platform were deleted successfully"
        );
        return respuesta;
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
