package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.show_roomDTO;
import com.sena.crud_basic.model.show_room;
import com.sena.crud_basic.repository.Ishow_room;

@Service
public class show_roomService {
    @Autowired
    private Ishow_room data;

    public void save(show_roomDTO show_roomDTO){
        show_room show_roomRegister = converToModel(show_roomDTO);
        data.save(show_roomRegister);
    }

    public show_roomDTO converToDTO(show_room show_room){
        show_roomDTO show_roomDTO = new show_roomDTO(
            show_room.getShow(),
            show_room.getRoom()
        );
        return show_roomDTO;
    }
    
    public show_room converToModel(show_roomDTO show_roomDTO){
        show_room show_room = new show_room(
            0,
            show_roomDTO.getShow(),
            show_roomDTO.getRoom()
        );
        return show_room;
    }
}
