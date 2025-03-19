package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.moviesDTO;
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

     public void save(moviesDTO moviesDTO){
        movies moviesRegister = converToModel(moviesDTO);
        data.save(moviesRegister);
     }

     public moviesDTO converToDTO(movies movies){
         moviesDTO moviesDTO = new moviesDTO(
             movies.getTitle(),
             movies.getTime_min(),
             movies.getLaunch_year()
         );
         return moviesDTO;
     }

     public movies converToModel(moviesDTO moviesDTO){
         movies movies = new movies(
            0,
             moviesDTO.getTitle(),
             moviesDTO.getTime_min(),
             moviesDTO.getLaunch_year()
         );
         return movies;
     }
}
