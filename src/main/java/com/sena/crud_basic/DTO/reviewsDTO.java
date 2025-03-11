package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.movies;
import com.sena.crud_basic.model.users;

public class reviewsDTO {

    private movies movie;

    private users user;

    private int rating;

    private String comment;

    public reviewsDTO(movies movie, users user, int rating, String comment){
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public movies getMovie_id(){
        return movie;
    }

    public void setMovie_id(movies movie){
        this.movie = movie;
    }

    public users getUser_id(){
        return user;
    }

    public void setUser_id(users user){
        this.user = user;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}