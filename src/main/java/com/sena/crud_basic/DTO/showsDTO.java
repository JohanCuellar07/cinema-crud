package com.sena.crud_basic.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import com.sena.crud_basic.model.movies;

public class showsDTO {

    private movies movie;

    private LocalDate date;

    private LocalTime time;

    private int price;

    public showsDTO(movies movie, LocalDate date, LocalTime time, int price){
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public movies getMovie_id(){
        return movie;
    }

    public void setMovie_id(movies movie){
        this.movie = movie;
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
