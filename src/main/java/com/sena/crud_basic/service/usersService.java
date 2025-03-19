package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.usersDTO;
import com.sena.crud_basic.model.users;
import com.sena.crud_basic.repository.Iusers;

@Service
public class usersService {
    
    @Autowired
    private Iusers data;

    public List<users> findAll(){
        return data.findAll();
    }

    public Optional<users> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteUser(int id){
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

    public responseDTO save(usersDTO usersDTO){
        if (usersDTO.getName().length() < 1 || usersDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        users usersRegister = converToModel(usersDTO);
        data.save(usersRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public usersDTO converToDTO(users users){
        usersDTO usersDTO = new usersDTO(
            users.getName(),
            users.getLastName(),
            users.getPhone(),
            users.getEmail()
        );
        return usersDTO;
    }

    public users converToModel(usersDTO usersDTO){
        users users = new users(
            0,
            usersDTO.getName(),
            usersDTO.getLastName(),
            usersDTO.getPhone(),
            usersDTO.getEmail()
        );
        return users;
    }
}
