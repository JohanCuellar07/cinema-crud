package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.actors;
import com.sena.crud_basic.model.directors;
import com.sena.crud_basic.model.genres;
import com.sena.crud_basic.model.movies;
import com.sena.crud_basic.model.platforms;

public interface Imovies extends JpaRepository<movies, Integer>{
    @Query("SELECT m FROM movies m WHERE m.status != false")
    List<movies> getLisMoviesActive();

    @Query("SELECT m FROM movies m WHERE m.title LIKE %?1%")
    List<movies> getListMoviesForTitle(String filter);

    /* 
    @Query("SELECT m FROM movies m WHERE m.launch_year LIKE %?1%")
    List<movies> getListMoviesForLaunchYear(String filter);
    */

    @Query(value = "SELECT g.* FROM genres g " +
                   "INNER JOIN movie_genre mg ON g.id = mg.genre_id " +
                   "WHERE mg.movie_id = :movieId", nativeQuery = true)
    List<genres> getGenresByMovieId(@Param("movieId") int movieId);

    @Query(value = "SELECT a.* FROM actors a " +
                   "INNER JOIN movie_actor ma ON a.id = ma.actor_id " +
                   "WHERE ma.movie_id = :movieId", nativeQuery = true)
    List<actors> getActorsByMovieId(@Param("movieId") int movieId);

    @Query(value = "SELECT d.* FROM directors d " +
                   "INNER JOIN movie_director md ON d.id = md.director_id " +
                   "WHERE md.movie_id = :movieId", nativeQuery = true)
    List<directors> getDirectorsByMovieId(@Param("movieId") int movieId);

    @Query(value = "SELECT p.* FROM platforms p " +
                   "INNER JOIN movie_platform mp ON p.id = mp.platform_id " +
                   "WHERE mp.movie_id = :movieId", nativeQuery = true)
    List<platforms> getPlatformsByMovieId(@Param("movieId") int movieId);
}
