package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.DTO.roomsDTO;
import com.sena.crud_basic.model.rooms;
import com.sena.crud_basic.repository.Irooms;

public class roomsService {
    @Autowired
    private Irooms data;

    public void save(roomsDTO roomsDTO){
        rooms roomsRegister = converToModel(roomsDTO);
        data.save(roomsRegister);

        public roomsDTO converToDTO(rooms rooms){
            roomsDTO roomsDTO = new roomsDTO(
                rooms.getCinema_id(),
                rooms.getNum_room(),
                rooms.getCapacity(),
                rooms.getType()
            );
            return roomsDTO;
        }
            
        public rooms converToModel(roomsDTO roomsDTO){
            rooms rooms = new rooms(
                0,
                roomsDTO.getCinema_id(),
                roomsDTO.getNum_room(),
                roomsDTO.getCapacity(),
                roomsDTO.getType()
            );
            return rooms;
        }
    }
}
