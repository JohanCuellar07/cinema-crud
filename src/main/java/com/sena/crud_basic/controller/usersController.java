package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.usersDTO;
import com.sena.crud_basic.service.usersService;

@RestController
@RequestMapping("/users")
public class usersController {
    @Autowired
    private usersService usersService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUsers(@RequestBody usersDTO users){
        usersService.save(users);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
}
