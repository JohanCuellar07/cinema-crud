package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "show_room")
public class show_room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private shows show;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private rooms room;

    public show_room(){}

    public show_room(int id, shows show, rooms room){
        this.id = id;
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
