package com.sena.crud_basic.DTO;

public class showsDTO {

    private int movie_id;

    private Date date;

    private Time time;

    private int price;

    public showsDTO(int movie_id, Date date, Time time, int price){
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

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
