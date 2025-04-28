package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.movie_actor;

import jakarta.transaction.Transactional;

public interface Imovie_actor extends JpaRepository<movie_actor, Integer> {
    @Query("SELECT ma FROM movie_genre ma WHERE ma.movie.id = :movieId")
    List<movie_actor> findAllByMovieId(@Param("movieId") int movieId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM movie_actor ma WHERE ma.movie.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);
}
