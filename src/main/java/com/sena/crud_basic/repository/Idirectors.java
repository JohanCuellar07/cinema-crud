package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.directors;

public interface Idirectors extends JpaRepository<directors, Integer> {
    @Query("SELECT d FROM directors d WHERE d.status != false")
    List<directors> getListDirectorsActive();

    @Query("SELECT d FROM directors d WHERE d.name LIKE %?1%")
    List<directors> getListDirectorsForName(String filter);

    @Query("SELECT d FROM directors d WHERE d.nationality LIKE %?1%")
    List<directors> getListDirectorsForNationality(String filter);
}
