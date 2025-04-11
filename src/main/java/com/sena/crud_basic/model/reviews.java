package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reviews")
public class reviews {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    @Column(name = "name_reviewer", length = 50)
    private String name_reviewer;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", length = 150)
    private String comment;

    @Column(name="status",nullable =false, columnDefinition = "boolean default true ")
    private boolean status;

    public reviews(){}

    public reviews(int id, movies movie, String name_reviewer, int rating, String comment, boolean status){
        this.id = id;
        this.movie = movie;
        this.name_reviewer = name_reviewer;
        this.rating = rating;
        this.comment = comment;
        this.status = status;
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

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}
