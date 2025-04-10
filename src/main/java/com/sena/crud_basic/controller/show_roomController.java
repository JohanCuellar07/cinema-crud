package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.show_roomDTO;
import com.sena.crud_basic.service.show_roomService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/show_room")
public class show_roomController {
    @Autowired
    private show_roomService show_roomService;

    @PostMapping("/")
    public ResponseEntity<Object> registerShow_Room(@RequestBody show_roomDTO show_room) {
        responseDTO respuesta = show_roomService.save(show_room);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShow_Room(@PathVariable int id, @RequestBody show_roomDTO dto) {
        responseDTO respuesta = show_roomService.updateShow_Room(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllShow_Room(){
        var listaFuncionesSalas = show_roomService.findAll();
        return new ResponseEntity<>(listaFuncionesSalas, HttpStatus.OK);
    }
}
