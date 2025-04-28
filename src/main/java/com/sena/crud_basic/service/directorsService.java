package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.directorsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.directors;
import com.sena.crud_basic.repository.Idirectors;

@Service
public class directorsService {
    @Autowired
    private Idirectors data;
    
    public List<directors> findAll(){
        return data.getListDirectorsActive();
    }

    public List<directors> getListDirectorsForName(String filter){
        return data.getListDirectorsForName(filter);
    }

    public List<directors> getListDirectorsForNationality(String filter){
        return data.getListDirectorsForNationality(filter);
    }

    public Optional<directors> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteDirector(int id){
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

    public responseDTO save(directorsDTO directorsDTO){
        if (directorsDTO.getName().length() < 1 || directorsDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (directorsDTO.getUrlImage().length() > 200) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "The url_image cannot exceed 200 characters"
            );
            return respuesta;
        }
        if (directorsDTO.getBirth() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "The birth cannot be null"
            );
            return respuesta;
        }
        if (directorsDTO.getNationality().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "The nationality cannot exceed 50 characters"
            );
            return respuesta;
        }
        directors directorsRegister = converToModel(directorsDTO);
        data.save(directorsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Was successfully saved"
        );
        return respuesta;
    }

    public responseDTO updateDirectors(int id, directorsDTO dto){
        Optional<directors> directorsOpt = data.findById(id);
        if (!directorsOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "The register does not exist"
            );
            return respuesta;
        }
        directors existingDirectors = directorsOpt.get();
        existingDirectors.setName(dto.getName());
        existingDirectors.setUrlImage(dto.getUrlImage());
        existingDirectors.setBirth(dto.getBirth());
        existingDirectors.setNationality(dto.getNationality());
        data.save(existingDirectors);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Was successfully updated"
        );
        return respuesta;
    }

    public directorsDTO converToDTO(directors directors){
        directorsDTO directorsDTO = new directorsDTO(
            directors.getName(),
            directors.getUrlImage(),
            directors.getBirth(),
            directors.getNationality()
        );
        return directorsDTO;
    }

    public directors converToModel(directorsDTO directorsDTO){
        directors directors = new directors(
            0,
            directorsDTO.getName(),
            directorsDTO.getUrlImage(),
            directorsDTO.getBirth(),
            directorsDTO.getNationality(),
            true
        );
        return directors;
    }
}
