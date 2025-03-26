package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.moviesDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.movies;
import com.sena.crud_basic.repository.Imovies;

@Service
public class moviesService {
    /*
     * save
     * findAll
     * findById
     * delete
     */

     /* establish connection with database */
    @Autowired
    private Imovies data;

    public List<movies> findAll(){
       //return data.findAll();
       return data.getLisMoviesActive();
    }

    public List<movies> getListMoviesForTitle(String filter){
        return data.getListMoviesForTitle(filter);
    }
    /* 
    public List<movies> getListMoviesForLaunchYear(String filter){
        return data.getListMoviesForLaunchYear(filter);
    }
    */

    public Optional<movies> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteMovie(int id){
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully deleted"
        );
        return respuesta;
    }

    public responseDTO save(moviesDTO moviesDTO){
        if (moviesDTO.getTitle().length() < 1 || moviesDTO.getTitle().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The title cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (moviesDTO.getDescription().length() > 200) {
            responseDTO respuesta = new responseDTO(
                null, 
                null
            );
            return respuesta;
        }
        /* 
        if (moviesDTO.getTime_min().length()) {
            responseDTO respuesta = new responseDTO(
                null,
                null
            );
            return respuesta;
        }
        if (moviesDTO.getLaunch_year().length()) {
            responseDTO respuesta = new responseDTO(
                null,
                null
            );
            return respuesta;
        }
        */
       movies moviesRegister = converToModel(moviesDTO);
       data.save(moviesRegister);
       responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public moviesDTO converToDTO(movies movies){
        moviesDTO moviesDTO = new moviesDTO(
            movies.getTitle(),
            movies.getDescription(),
            movies.getTime_min(),
            movies.getLaunch_year()
        );
        return moviesDTO;
    }

    public movies converToModel(moviesDTO moviesDTO){
        movies movies = new movies(
           0,
            moviesDTO.getTitle(),
            moviesDTO.getDescription(),
            moviesDTO.getTime_min(),
            moviesDTO.getLaunch_year(),
            true
        );
        return movies;
    }
}
