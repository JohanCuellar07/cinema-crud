package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.rooms;
import com.sena.crud_basic.model.shows;

public class show_roomDTO {

    private shows show;

    private rooms room;

    public show_roomDTO(shows show, rooms room){
        this.show = show;
        this.room = room;
    }

    public shows getShow(){
        return show;
    }

    public void setShow(shows show){
        this.show = show;
    }

    public rooms getRoom(){
        return room;
    }

    public void setRoom(rooms room){
        this.room = room;
    }
}
