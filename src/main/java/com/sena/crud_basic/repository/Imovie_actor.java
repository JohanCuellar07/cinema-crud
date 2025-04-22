package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.movie_actor;

public interface Imovie_actor extends JpaRepository<movie_actor, Integer> {

}
