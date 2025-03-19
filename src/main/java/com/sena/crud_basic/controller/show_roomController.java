package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.show_roomDTO;
import com.sena.crud_basic.service.show_roomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/show_room")
public class show_roomController {
    @Autowired
    private show_roomService show_roomService;

    @PostMapping("/")
    public ResponseEntity<Object> registerShow_Room(@RequestBody show_roomDTO show_room) {
        show_roomService.save(show_room);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}
