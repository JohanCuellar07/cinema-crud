package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.roomsDTO;
import com.sena.crud_basic.service.roomsService;

@RestController
@RequestMapping("/rooms")
public class roomsController {
    @Autowired
    private roomsService roomsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerRooms(@RequestBody roomsDTO rooms){
        roomsService.save(rooms);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
