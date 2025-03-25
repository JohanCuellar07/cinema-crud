package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.roomsDTO;
import com.sena.crud_basic.service.roomsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rooms")
public class roomsController {
    
    @Autowired
    private roomsService roomsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerRooms(@RequestBody roomsDTO rooms){
        responseDTO respuesta = roomsService.save(rooms);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllRooms() {
        var listaSalas = roomsService.findAll();
        return new ResponseEntity<>(listaSalas, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRoom(@PathVariable int id) {
        var sala = roomsService.findById(id);
        if (!sala.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoom(@PathVariable int id){
        var message = roomsService.deleteRoom(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
