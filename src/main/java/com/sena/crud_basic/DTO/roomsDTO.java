package com.sena.crud_basic.DTO;

public class roomsDTO {

    private int cinema_id;

    private String num_room;

    private int capacity;

    private String type;

    public roomsDTO(int cinema_id, String num_room, int capacity, String type){
        this.cinema_id = cinema_id;
        this.num_room = num_room;
        this.capacity = capacity;
        this.type = type;
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
