package com.sena.crud_basic.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class showsDTO {

    private int movie_id;

    private LocalDate date;

    private LocalTime time;

    private int price;

    public showsDTO(int movie_id, LocalDate date, LocalTime time, int price){
        this.movie_id = movie_id;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public int getMovie_id(){
        return movie_id;
    }

    public void setMovie_id(int movie_id){
        this.movie_id = movie_id;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalTime getTime(){
        return time;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
