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
        //return data.findAll();
        return data.getListUsersActive();
    }

    public List<users> getListUsersForName(String filter){
        //return data.findAll();
        return data.getListUsersForName(filter);
    }

    public List<users> getListUsersForLastName(String filter){
        return data.getListUsersForLastName(filter);
    }

    public List<users> getListUsersForEmail(String filter){
        return data.getListUsersForEmail(filter);
    }

    public List<users> getListUsersForPhone(String filter){
        return data.getListUsersForPhone(filter);
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
        if (usersDTO.getLastName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The last name cannot exceed 50 characters"
            );
            return respuesta;
        }
        if (usersDTO.getPhone().length() > 10) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The phone cannot exceed 10 characters"
            );
            return respuesta;
        }
        if (usersDTO.getEmail().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The email cannot exceed 100 characters"
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

    public responseDTO updateUsers(int id, usersDTO dto){
        Optional<users> usersOpt = data.findById(id);
        if (!usersOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        users existingUsers = usersOpt.get();
        existingUsers.setName(dto.getName());
        existingUsers.setLastName(dto.getLastName());
        existingUsers.setPhone(dto.getPhone());
        existingUsers.setEmail(dto.getEmail());
        data.save(existingUsers);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
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
            usersDTO.getEmail(),
            true
        );
        return users;
    }
}
