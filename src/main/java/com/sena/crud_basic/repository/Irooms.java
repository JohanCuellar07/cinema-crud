package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.rooms;

public interface Irooms extends JpaRepository<rooms, Integer>{
    @Query("SELECT r FROM rooms r WHERE r.status != false")
    List<rooms> getListRoomsActive();

    @Query("SELECT r FROM rooms r WHERE r.type LIKE %?1%")
    List<rooms> getListRoomsForType(String filter);
    /* 
    @Query("SELECT r FROM rooms r WHERE r.capacity LIKE %?1%")
    List<rooms> getListRoomsForCapacity(String filter);
    */
}
