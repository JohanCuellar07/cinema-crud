package com.sena.crud_basic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "shows")
public class shows {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = " movie_id", nullable = false)
    private movies movie;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "price", nullable = false)
    private int price;

    public shows(){}

    public shows(int id, movies movie, LocalDate date, LocalTime time, int price){
        this.id = id;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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
