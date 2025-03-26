package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.genres;

public interface Igenres extends JpaRepository<genres, Integer>{
    @Query("SELECT g FROM genres g WHERE g.status != false")
    List<genres> getListGenresActive();

    @Query("SELECT g FROM genres g WHERE g.name LIKE %?1%")
    List<genres> getListGenresForName(String filter);
}
