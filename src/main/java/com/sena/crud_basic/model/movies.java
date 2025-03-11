package com.sena.crud_basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "movies")
public class movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "time_min")
    private int time_min;

    @Column(name = "launch_year")
    private java.sql.Date launch_year;

    public movies(int id, String title, int time_min, java.sql.Date launch_year) {
        this.title = title;
        this.time_min = time_min;
        this.launch_year = launch_year;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime_min() {
        return time_min;
    }

    public void setTime_min(int time_min) {
        this.time_min = time_min;
    }

    public java.sql.Date getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(java.sql.Date launch_year) {
        this.launch_year = launch_year;
    }
}