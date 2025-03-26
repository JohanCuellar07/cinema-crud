package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.shows;

public interface Ishows extends JpaRepository<shows, Integer>{
    @Query("SELECT s FROM shows s WHERE s.status != false")
    List<shows> getListShowsActive();
}
