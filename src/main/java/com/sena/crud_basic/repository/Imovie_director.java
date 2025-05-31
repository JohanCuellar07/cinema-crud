package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.movie_director;

import jakarta.transaction.Transactional;

public interface Imovie_director extends JpaRepository<movie_director, Integer> {
    @Query("SELECT md FROM movie_director md WHERE md.movie.id = :movieId")
    List<movie_director> findAllByMovieId(@Param("movieId") int movieId);

    @Query("SELECT md FROM movie_director md WHERE md.director.id = :directorId")
    List<movie_director> findAllByDirectorId(@Param("directorId") int directorId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM movie_director md WHERE md.movie.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);

    @Transactional
    @Modifying
    @Query("DELETE FROM movie_director md WHERE md.director.id = :directorId")
    void deleteByDirectorId(@Param("directorId") int directorId);
}
