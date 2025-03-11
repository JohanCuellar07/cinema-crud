package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.reviews;

public interface Ireviews extends JpaRepository<reviews, Integer>{
    
}
