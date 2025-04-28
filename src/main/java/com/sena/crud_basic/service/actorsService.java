package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.actorsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.actors;
import com.sena.crud_basic.repository.Iactors;

@Service
public class actorsService {
    @Autowired
    private Iactors data;

    public List<actors> findAll(){
        return data.getListActorsActive();
    }

    public List<actors> getListActorsForName(String filter){
        return data.getListActorsForName(filter);
    }

    public List<actors> getListActorsForNationality(String filter){
        return data.getListActorsForNationality(filter);
    }

    public Optional<actors> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteActor(int id){
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

    public responseDTO save(actorsDTO actorsDTO){
        if (actorsDTO.getName().length() < 1 || actorsDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (actorsDTO.getUrlImage().length() > 200) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The url_image cannot exceed 200 characters"
            );
            return respuesta;
        }
        if (actorsDTO.getBirth() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The birth cannot be null"
            );
            return respuesta;
        }
        if (actorsDTO.getNationality().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The nationality cannot exceed 50 characters"
            );
            return respuesta;
        }
        actors actorsRegister = converToModel(actorsDTO);
        data.save(actorsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public responseDTO updateActors(int id, actorsDTO dto){
        Optional<actors> actorsOpt = data.findById(id);
        if (!actorsOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        actors existingActors = actorsOpt.get();
        existingActors.setName(dto.getName());
        existingActors.setUrlImage(dto.getUrlImage());
        existingActors.setBirth(dto.getBirth());
        existingActors.setNationality(dto.getNationality());
        data.save(existingActors);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
    }

    public actorsDTO converToDTO(actors actors){
        actorsDTO actorsDTO = new actorsDTO(
            actors.getName(),
            actors.getUrlImage(),
            actors.getBirth(),
            actors.getNationality()
        );
        return actorsDTO;
    }

    public actors converToModel(actorsDTO actorsDTO){
        actors actors = new actors(
            0,
            actorsDTO.getName(),
            actorsDTO.getUrlImage(),
            actorsDTO.getBirth(),
            actorsDTO.getNationality(),
            true
        );
        return actors;
    }
}
