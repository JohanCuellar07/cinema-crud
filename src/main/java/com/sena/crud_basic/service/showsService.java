package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.DTO.showsDTO;
import com.sena.crud_basic.model.shows;
import com.sena.crud_basic.repository.Ishows;

public class showsService {
    @Autowired
    private Ishows data;

    public void save(showsDTO showsDTO){
        shows showsRegister = converToModel(showsDTO);
        data.save(showsRegister);

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
}
