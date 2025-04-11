package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.actors;

public interface Iactors extends JpaRepository<actors, Integer> {
    @Query("SELECT a FROM actors a WHERE a.status != false")
    List<actors> getListActorsActive();

    @Query("SELECT a FROM actors a WHERE a.name LIKE %?1%")
    List<actors> getListActorsForName(String filter);
}
