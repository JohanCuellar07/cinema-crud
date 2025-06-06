package com.sena.crud_basic.model;

import java.time.LocalDate;
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

    @Column(name = "url_image", length = 200, nullable = false)
    private String url_image;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "time_min", nullable = false)
    private int time_min;

    @Column(name = "launch_year", nullable = false)
    private LocalDate launch_year;

    @Column(name="status",nullable =false, columnDefinition = "boolean default true ")
    private boolean status;

    public movies(){}

    public movies(int id, String title, String url_image, String description, int time_min, LocalDate launch_year, boolean status) {
        this.id = id;
        this.title = title;
        this.url_image = url_image;
        this.description = description;
        this.time_min = time_min;
        this.launch_year = launch_year;
        this.status = status;
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

    public String getUrlImage(){
        return url_image;
    }

    public void setUrlImage(String url_image){
        this.url_image = url_image;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getTime_min() {
        return time_min;
    }

    public void setTime_min(int time_min) {
        this.time_min = time_min;
    }

    public LocalDate getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(LocalDate launch_year) {
        this.launch_year = launch_year;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}