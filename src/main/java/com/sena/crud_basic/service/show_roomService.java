package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.show_roomDTO;
import com.sena.crud_basic.model.show_room;
import com.sena.crud_basic.repository.Ishow_room;

@Service
public class show_roomService {
    @Autowired
    private Ishow_room data;

    public List<show_room> findAll(){
        return data.findAll();
    }

    public Optional<show_room> findById(int id){
        return data.findById(id);
    }

    public responseDTO save(show_roomDTO show_roomDTO){
        if (show_roomDTO.getShow() == null || show_roomDTO.getRoom() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The show or room cannot be null"
            );
            return respuesta;
        }
        show_room show_roomRegister = converToModel(show_roomDTO);
        data.save(show_roomRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public responseDTO updateShow_Room(int id, show_roomDTO dto){
        Optional<show_room> show_roomOpt = data.findById(id);
        if (!show_roomOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        show_room existingShowRoom = show_roomOpt.get();
        existingShowRoom.setShow(dto.getShow());
        existingShowRoom.setRoom(dto.getRoom());
        data.save(existingShowRoom);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
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
