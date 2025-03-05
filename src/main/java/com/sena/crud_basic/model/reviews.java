package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "reviews")
public class reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "movie_id", nullable = false)
    private int movie_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", length = 150)
    private String comment;
}
