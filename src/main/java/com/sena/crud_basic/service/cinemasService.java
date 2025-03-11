package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.DTO.cinemasDTO;
import com.sena.crud_basic.model.cinemas;
import com.sena.crud_basic.repository.Icinemas;

public class cinemasService {
    @Autowired
    private Icinemas data;
    public void save(cinemasDTO cinemasDTO){
        cinemas cinemasRegister = converToModel(cinemasDTO);
        data.save(cinemasRegister);

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
                cinemasDTO.getPhone()
            );
            return cinemas;
        }
    }
}
