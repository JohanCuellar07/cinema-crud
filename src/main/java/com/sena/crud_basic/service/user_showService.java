package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.user_showDTO;
import com.sena.crud_basic.model.user_show;
import com.sena.crud_basic.repository.Iuser_show;

@Service
public class user_showService {
    @Autowired
    private Iuser_show data;

    public void save(user_showDTO user_showDTO){
        user_show user_showRegister = converToModel(user_showDTO);
        data.save(user_showRegister);
    }

    public user_showDTO converToDTO(user_show user_show){
        user_showDTO user_showDTO = new user_showDTO(
            user_show.getShow_id(),
            user_show.getUser_id()
        );
        return user_showDTO;
    }

    public user_show converToModel(user_showDTO user_showDTO){
        user_show user_show = new user_show(
            0,
            user_showDTO.getShow_id(),
            user_showDTO.getUser_id()
        );
        return user_show;
    }
}
