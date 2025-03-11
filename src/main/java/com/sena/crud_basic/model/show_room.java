package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "show_room")
public class show_room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @Column(name = "show_id", nullable = false)
    private shows show;

    @ManyToOne
    @Column(name = "room_id", nullable = false)
    private rooms room;

    public show_room(shows show, rooms room){
        this.show = show;
        this.room = room;
    }

    public int getShow(){
        return show;
    }

    public void setShow(shows show){
        this.show = show;
    }

    public int getRoom(){
        return room;
    }

    public void setRoom(rooms room){
        this.room = room;
    }
}
