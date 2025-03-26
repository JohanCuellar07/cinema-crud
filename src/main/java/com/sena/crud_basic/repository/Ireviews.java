package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.reviews;

public interface Ireviews extends JpaRepository<reviews, Integer>{
    @Query("SELECT r FROM reviews r WHERE r.status != false")
    List<reviews> getListReviewsActive();
    /* 
    @Query("SELECT r FROM reviews r WHERE r.movie_id LIKE %?1%")
    List<reviews> getListReviewsForMovie(String filter);

    @Query("SELECT r FROM reviews r WHERE r.user_id LIKE %?1%")
    List<reviews> getListReviewsForUser(String filter);

    @Query("SELECT r FROM reviews r WHERE r.rating LIKE %?1%")
    List<reviews> getListReviewsForRating(String filter);
    */
}
