package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "rooms")
public class rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @Column(name = "cinema_id", nullable = false)
    private int cinema_id;

    @Column(name = "num_room", length = 3)
    private String num_room;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "type", length = 30)
    private String type;

    public rooms(int id,int cinema_id, String num_room, int capacity, String type){
        this.cinema_id = cinema_id;
        this.num_room = num_room;
        this.capacity = capacity;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getCinema_id(){
        return cinema_id;
    }

    public void setCinema_id(int cinema_id){
        this.cinema_id = cinema_id;
    }

    public String getNum_room(){
        return num_room;
    }

    public void setNum_room(String num_room){
        this.num_room = num_room;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
