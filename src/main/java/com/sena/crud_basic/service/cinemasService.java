package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.cinemasDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.cinemas;
import com.sena.crud_basic.repository.Icinemas;

@Service
public class cinemasService {
    
    @Autowired
    private Icinemas data;

    public List<cinemas> findAll(){
        //return data.findAll();
        return data.getListCinemasActive();
    }

    public List<cinemas> getListCinemasForName(String filter){
        return data.getListCinemasForName(filter);
    }

    public List<cinemas> getListCinemasForAddress(String filter){
        return data.getListCinemasForAddress(filter);
    }

    public List<cinemas> getListCinemasForPhone(String filter){
        return data.getListCinemasForPhone(filter);
    }

    public Optional<cinemas> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteCinema(int id){
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

    public responseDTO save(cinemasDTO cinemasDTO){
        if (cinemasDTO.getName().length() < 1 || cinemasDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name must be between 1 and 50 characters"
            );
            return respuesta;
        }
        if (cinemasDTO.getAddress().length() > 70) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The address cannot exceed 70 characters"
            );
            return respuesta;
        }
        if (cinemasDTO.getPhone().length() > 10) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The phone cannot exceed 10 characters"
            );
            return respuesta;
        }
        cinemas cinemasRegister = converToModel(cinemasDTO);
        data.save(cinemasRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully registered"
        );
        return respuesta;
    }

    public cinemasDTO converToDTO(cinemas cinemas){
        cinemasDTO cinemasDTO = new cinemasDTO(
            cinemas.getName(),
            cinemas.getAddress(),
            cinemas.getPhone()
        );
        return cinemasDTO;
    }

    public cinemas converToModel(cinemasDTO cinemasDTO){
        cinemas cinemas = new cinemas(
            0,
            cinemasDTO.getName(),
            cinemasDTO.getAddress(),
            cinemasDTO.getPhone(),
            true
        );
        return cinemas;
    }
}
