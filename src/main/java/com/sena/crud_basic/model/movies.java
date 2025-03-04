package com.sena.crud_basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

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
    private Date launch_year;

    public movies(String title, int time_min, Date launch_year) {
        this.title = title;
        this.time_min = time_min;
        this.launch_year = launch_year;
    }

    public int getId(){
        return id;
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

    public void time_min(int time_min) {
        this.time_min = time_min;
    }

    public Date getLaunch_Year() {
        return launch_year;
    }

    public void launch_year(Date launch_year) {
        this.launch_year = launch_year;
    }
}