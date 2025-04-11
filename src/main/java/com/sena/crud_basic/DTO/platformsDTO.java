package com.sena.crud_basic.DTO;

public class platformsDTO {
    private String name;

    private String url_web;

    public platformsDTO(String name, String url_web) {
        this.name = name;
        this.url_web = url_web;
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
}
