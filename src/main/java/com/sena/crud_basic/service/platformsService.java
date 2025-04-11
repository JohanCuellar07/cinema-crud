package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.platformsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.platforms;
import com.sena.crud_basic.repository.Iplatforms;

@Service
public class platformsService {
    @Autowired
    private Iplatforms data;

    public List<platforms> findAll(){
        return data.getListPlatformsActive();
    }

    public List<platforms> getListPlatformsForName(String filter){
        return data.getListPlatformsForName(filter);
    }

    public Optional<platforms> findById(int id){
        return data.findById(id);
    }

    public responseDTO deletePlatform(int id){
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

    public responseDTO save(platformsDTO platformsDTO){
        if (platformsDTO.getName().length() < 1 || platformsDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (platformsDTO.getUrlWeb().length() > 200) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The url_web cannot exceed 200 characters"
            );
            return respuesta;
        }
        platforms platformsRegister = converToModel(platformsDTO);
        data.save(platformsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
            );
        return respuesta;
    }

    public responseDTO updatePlatforms(int id, platformsDTO dto){
        Optional<platforms> platformsOpt = data.findById(id);
        if (!platformsOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        platforms existingPlatforms = platformsOpt.get();
        existingPlatforms.setName(dto.getName());
        existingPlatforms.setUrlWeb(dto.getUrlWeb());
        data.save(existingPlatforms);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
    }

    public platformsDTO converToDTO(platforms platforms){
        platformsDTO platformsDTO = new platformsDTO(
            platforms.getName(),
            platforms.getUrlWeb()
        );
        return platformsDTO;
    }

    public platforms converToModel(platformsDTO platformsDTO){
        platforms platforms = new platforms(
            0,
            platformsDTO.getName(),
            platformsDTO.getUrlWeb(),
            true
        );
        return platforms;
    }
}
