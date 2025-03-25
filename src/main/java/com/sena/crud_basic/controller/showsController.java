package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.showsDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/shows")
public class showsController {
    
    @Autowired
    private com.sena.crud_basic.service.showsService showsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerShows(@RequestBody showsDTO shows){
        responseDTO respuesta = showsService.save(shows);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllShows() {
        var listaFunciones = showsService.findAll();
        return new ResponseEntity<>(listaFunciones, HttpStatus.OK);
    }
    
    @GetMapping("/{ID}")
    public ResponseEntity<Object> getOneShow(@PathVariable int id) {
        var funcion = showsService.findById(id);
        if (!funcion.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(funcion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShow(@PathVariable int id){
        var message = showsService.deleteShow(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
