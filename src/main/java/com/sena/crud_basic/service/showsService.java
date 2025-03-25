package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.showsDTO;
import com.sena.crud_basic.model.shows;
import com.sena.crud_basic.repository.Ishows;

@Service
public class showsService {

    @Autowired
    private Ishows data;

    public List<shows> findAll(){
        return data.findAll();
    }

    public Optional<shows> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteShow(int id){
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

    public responseDTO save(showsDTO showsDTO){
        if (showsDTO.getMovie_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The movie_id cannot be empty"
            );
            return respuesta;
        }
        if (showsDTO.getDate() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The date cannot be empty"
            );
            return respuesta;
        }
        if (showsDTO.getTime() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The time cannot be empty"
            );
            return respuesta;
        }
        if (showsDTO.getPrice() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The price cannot be empty"
            );
            return respuesta;
        }
        shows showsRegister = converToModel(showsDTO);
        data.save(showsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public showsDTO converToDTO(shows shows){
        showsDTO showsDTO = new showsDTO(
            shows.getMovie_id(),
            shows.getDate(),
            shows.getTime(),
            shows.getPrice()
        );
        return showsDTO;
    }

    public shows converToModel(showsDTO showsDTO){
        shows shows = new shows(
            0,
            showsDTO.getMovie_id(),
            showsDTO.getDate(),
            showsDTO.getTime(),
            showsDTO.getPrice()
        );
        return shows;
    }
}
