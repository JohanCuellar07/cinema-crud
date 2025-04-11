package com.sena.crud_basic.DTO;

import java.time.LocalDate;

public class actorsDTO {
    private String name;

    private String url_image;

    private LocalDate birth;

    private String nationality;

    public actorsDTO(String name, String url_image, LocalDate birth, String nationality) {
        this.name = name;
        this.url_image = url_image;
        this.birth = birth;
        this.nationality = nationality;
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
}
