package com.sena.crud_basic.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "directors")
public class directors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "url_image", length = 200, nullable = false)
    private String url_image;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name="status",nullable =false, columnDefinition = "boolean default true ")
    private boolean status;

    public directors(){}

    public directors(int id, String name, String url_image, LocalDate birth, String nationality, boolean status) {
        this.id = id;
        this.name = name;
        this.url_image = url_image;
        this.birth = birth;
        this.nationality = nationality;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return url_image;
    }

    public void setUrlImage(String url_image){
        this.url_image = url_image;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
