package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.DTO.usersDTO;
import com.sena.crud_basic.model.users;
import com.sena.crud_basic.repository.Iusers;

public class usersService {
    @Autowired
    private Iusers data;

    public void save(usersDTO usersDTO){
        users usersRegister = converToModel(usersDTO);
        data.save(usersRegister);
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
