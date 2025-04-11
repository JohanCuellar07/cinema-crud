package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.platforms;

public interface Iplatforms extends JpaRepository<platforms, Integer> {
    @Query("SELECT p FROM platforms p WHERE p.status != false")
    List<platforms> getListPlatformsActive();

    @Query("SELECT p FROM platforms p WHERE p.name LIKE %?1%")
    List<platforms> getListPlatformsForName(String filter);
}
