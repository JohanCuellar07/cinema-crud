package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.cinemas;

public class roomsDTO {

    private cinemas cinema;

    private String num_room;

    private int capacity;

    private String type;

    public roomsDTO(cinemas cinema, String num_room, int capacity, String type){
        this.cinema = cinema;
        this.num_room = num_room;
        this.capacity = capacity;
        this.type = type;
    }

    public cinemas getCinema_id(){
        return cinema;
    }

    public void setCinema_id(cinemas cinema){
        this.cinema = cinema;
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
