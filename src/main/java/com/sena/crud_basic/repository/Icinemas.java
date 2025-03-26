package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.cinemas;

public interface Icinemas extends JpaRepository<cinemas, Integer>{
    @Query("SELECT c FROM cinemas c WHERE c.status != false")
    List<cinemas> getListCinemasActive();

    @Query("SELECT c FROM cinemas c WHERE c.name LIKE %?1%")
    List<cinemas> getListCinemasForName(String filter);

    @Query("SELECT c FROM cinemas c WHERE c.address LIKE %?1%")
    List<cinemas> getListCinemasForAddress(String filter);

    @Query("SELECT c FROM cinemas c WHERE c.phone LIKE %?1%")
    List<cinemas> getListCinemasForPhone(String filter);
}
