package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.movie_genre;

import jakarta.transaction.Transactional;

public interface Imovie_genre extends JpaRepository<movie_genre, Integer>{
    @Query("SELECT mg FROM movie_genre mg WHERE mg.movie.id = :movieId")
    List<movie_genre> findAllByMovieId(@Param("movieId") int movieId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM movie_genre mg WHERE mg.movie.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);
}
