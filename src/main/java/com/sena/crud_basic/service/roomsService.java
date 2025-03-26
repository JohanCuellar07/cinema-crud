package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.roomsDTO;
import com.sena.crud_basic.model.rooms;
import com.sena.crud_basic.repository.Irooms;

@Service
public class roomsService {
    
    @Autowired
    private Irooms data;

    public List<rooms> findAll(){
        //return data.findAll();
        return data.getListRoomsActive();
    }

    public List<rooms> getListRoomsForType(String filter){
        return data.getListRoomsForType(filter);
    }

    public List<rooms> getListRoomsForCapacity(String filter){
        return data.getListRoomsForCapacity(filter);
    }

    public Optional<rooms> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteRoom(int id){
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully deleted"
        );
        return respuesta;
    }

    public responseDTO save(roomsDTO roomsDTO){
        if (roomsDTO.getCinema_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The cinema_id cannot be empty"
            );
            return respuesta;
        }
        if (roomsDTO.getNum_room().length() < 1 || roomsDTO.getNum_room().length() > 3) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The num_room cannot be empty or exceed 3 characters"
            );
            return respuesta;
        }
        if (roomsDTO.getType().length() > 30) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The type cannot exceed 30 characters"
            );
            return respuesta;
        }
        rooms roomsRegister = converToModel(roomsDTO);
        data.save(roomsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

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
            roomsDTO.getType(),
            true
        );
        return rooms;
    }
}
