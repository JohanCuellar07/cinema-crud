package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.movies;

public interface Imovies extends JpaRepository<movies, Integer>{
    @Query("SELECT m FROM movies m WHERE m.status != false")
    List<movies> getLisMoviesActive();

    @Query("SELECT m FROM movies m WHERE m.title LIKE %?1%")
    List<movies> getListMoviesForTitle(String filter);
    /* 
    @Query("SELECT m FROM movies m WHERE m.launch_year LIKE %?1%")
    List<movies> getListMoviesForLaunchYear(String filter);
    */
    /*
     * C
     * R
     * U
     * D
     */
}
