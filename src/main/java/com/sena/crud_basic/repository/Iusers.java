package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.users;

public interface Iusers extends JpaRepository<users, Integer>{
    @Query("SELECT u FROM users u WHERE u.status != false")
    List<users> getListUsersActive();

    @Query("SELECT u FROM users u WHERE u.name LIKE %?1%")
    List<users> getListUsersForName(String filter);

    @Query("SELECT u FROM users u WHERE u.last_name LIKE %?1%")
    List<users> getListUsersForLastName(String filter);

    @Query("SELECT u FROM users u WHERE u.email LIKE %?1%")
    List<users> getListUsersForEmail(String filter);

    @Query("SELECT u FROM users u WHERE u.phone LIKE %?1%")
    List<users> getListUsersForPhone(String filter);

    /*
     * C
     * R
     * U
     * D
     */
}
