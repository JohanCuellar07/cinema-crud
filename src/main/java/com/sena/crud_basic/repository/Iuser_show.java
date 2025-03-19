package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.user_show;

public interface Iuser_show extends JpaRepository<user_show, Integer>{
    
}
