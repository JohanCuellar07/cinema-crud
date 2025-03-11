package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.DTO.genresDTO;
import com.sena.crud_basic.model.genres;
import com.sena.crud_basic.repository.Igenres;

public class genresService {
    @Autowired
    private Igenres data;

    public void save(genresDTO genresDTO){
        genres genresRegister = converToModel(genresDTO);
        data.save(genresRegister);
    }

    public genresDTO converToDTO(genres genres){
        genresDTO genresDTO = new genresDTO(
            genres.getName(),
            genres.getDescription()
        );
        return genresDTO;
    }

    public genres converToModel(genresDTO genresDTO){
        genres genres = new genres(
            0,
            genresDTO.getName(),
            genresDTO.getDescription()
        );
        return genres;
    }
}
