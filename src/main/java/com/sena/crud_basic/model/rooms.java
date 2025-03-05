package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "rooms")
public class rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "cinema_id", nullable = false)
    private int cinema_id;

    @Column(name = "num_room", length = 3)
    private String num_room;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "type", length = 30)
    private String type;
}
