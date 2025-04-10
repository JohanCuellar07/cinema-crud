package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.user_showDTO;
import com.sena.crud_basic.model.user_show;
import com.sena.crud_basic.repository.Iuser_show;

@Service
public class user_showService {
    @Autowired
    private Iuser_show data;

    public List<user_show> findAll(){
        return data.findAll();
    }

    public Optional<user_show> findById(int id){
        return data.findById(id);
    }

    public responseDTO save(user_showDTO user_showDTO){
        if (user_showDTO.getShow_id() == null || user_showDTO.getUser_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The show_id or user_id cannot be null"
            );
            return respuesta;
        }
        user_show user_showRegister = converToModel(user_showDTO);
        data.save(user_showRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public responseDTO updateUser_Show(int id, user_showDTO dto){
        Optional<user_show> user_showOpt = data.findById(id);
        if (!user_showOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        user_show existingUserShow = user_showOpt.get();
        existingUserShow.setShow_id(dto.getShow_id());
        existingUserShow.setUser_id(dto.getUser_id());
        data.save(existingUserShow);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
    }

    public user_showDTO converToDTO(user_show user_show){
        user_showDTO user_showDTO = new user_showDTO(
            user_show.getShow_id(),
            user_show.getUser_id()
        );
        return user_showDTO;
    }

    public user_show converToModel(user_showDTO user_showDTO){
        user_show user_show = new user_show(
            0,
            user_showDTO.getShow_id(),
            user_showDTO.getUser_id()
        );
        return user_show;
    }
}
