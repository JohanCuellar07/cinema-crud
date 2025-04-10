package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.genresDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.genres;
import com.sena.crud_basic.repository.Igenres;

@Service
public class genresService {
    
    @Autowired
    private Igenres data;

    public List<genres> findAll(){
        //return data.findAll();
        return data.getListGenresActive();
    }

    public List<genres> getListGenresForName(String filter){
        return data.getListGenresForName(filter);
    }

    public Optional<genres> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteGenre(int id){
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
            "Was succesfully deleted"
        );
        return respuesta;
    }

    public responseDTO save(genresDTO genresDTO){
        if (genresDTO.getName().length() < 1 || genresDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (genresDTO.getDescription().length() > 200) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The description cannot exceed 200 characters"
            );
            return respuesta;
        }
        genres genresRegister = converToModel(genresDTO);
        data.save(genresRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was succesfully saved"
        );
        return respuesta;
    }

    public responseDTO updateGenres(int id, genresDTO dto){
        Optional<genres> genresOpt = data.findById(id);
        if (!genresOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        genres existingGenres = genresOpt.get();
        existingGenres.setName(dto.getName());
        existingGenres.setDescription(dto.getDescription());
        data.save(existingGenres);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
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
            genresDTO.getDescription(),
            true
        );
        return genres;
    }
}
