package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.movies;

public class reviewsDTO {

    private movies movie;

    private String name_reviewer;

    private int rating;

    private String comment;

    public reviewsDTO(movies movie, String name_reviewer, int rating, String comment){
        this.movie = movie;
        this.name_reviewer = name_reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public movies getMovie_id(){
        return movie;
    }

    public void setMovie_id(movies movie){
        this.movie = movie;
    }

    public String getNameReviewer(){
        return name_reviewer;
    }

    public void setNameReviewer(String name_reviewer){
        this.name_reviewer = name_reviewer;
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