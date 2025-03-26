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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", length = 150)
    private String comment;

    @Column(name="status",nullable =false, columnDefinition = "boolean default true ")
    private boolean status;

    public reviews(){}

    public reviews(int id, movies movie, users user, int rating, String comment, boolean status){
        this.id = id;
        this.movie = movie;
        this.user = user;
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

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}
