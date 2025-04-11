package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "platforms")
public class platforms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "url_web", length = 200, nullable = false)
    private String url_web;

    @Column(name="status",nullable =false, columnDefinition = "boolean default true ")
    private boolean status;

    public platforms(){}

    public platforms(int id, String name, String url_web, boolean status) {
        this.id = id;
        this.name = name;
        this.url_web = url_web;
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

    public String getUrlWeb() {
        return url_web;
    }

    public void setUrlWeb(String url_web){
        this.url_web = url_web;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
