package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.users;

public interface Iusers extends JpaRepository<users, Integer>{

}
