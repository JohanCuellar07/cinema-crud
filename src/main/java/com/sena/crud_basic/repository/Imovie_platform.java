package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.movie_platform;

import jakarta.transaction.Transactional;

public interface Imovie_platform extends JpaRepository<movie_platform, Integer> {
    @Query("SELECT mp FROM movie_platform mp WHERE mp.movie.id = :movieId")
    List<movie_platform> findAllByMovieId(@Param("movieId") int movieId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM movie_platform mp WHERE mp.movie.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);
}
