package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "reviews")
public class reviews {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @Column(name = "movie_id", nullable = false)
    private int movie_id;

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", length = 150)
    private String comment;

    public reviews(int id, int movie_id, int user_id, int rating, String comment){
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getMovie_id(){
        return movie_id;
    }

    public void setMovie_id(int movie_id){
        this.movie_id = movie_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
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
