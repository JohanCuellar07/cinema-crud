package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.shows;
import com.sena.crud_basic.model.users;

public class user_showDTO {

    private shows show;

    private users user;

    public user_showDTO(shows show, users user){
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
