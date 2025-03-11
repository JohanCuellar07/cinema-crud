package com.sena.crud_basic.DTO;

public class usersDTO {

    private String name;

    private String last_name;

    private String phone;

    private String email;

    public usersDTO(String name, String last_name, String phone, String email){
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return last_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
