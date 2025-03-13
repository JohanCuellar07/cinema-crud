package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "user_show")
public class user_show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private shows show;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user;

    public user_show(shows show, users user){
        this.show = show;
        this.user = user;
    }

    public shows getShow_id(){
        return show;
    }

    public void setShow_id(shows show){
        this.show = show;
    }

    public users getUser_id(){
        return user;
    }

    public void setUser_id(users user){
        this.user = user;
    }
}
