package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.directorsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.directorsService;

@RestController
@RequestMapping("/directors")
public class directorsController {
    @Autowired
    private directorsService directorsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerDirectors(@RequestBody directorsDTO directors){
        responseDTO respuesta = directorsService.save(directors);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDirectors(@PathVariable int id, @RequestBody directorsDTO dto) {
        responseDTO respuesta = directorsService.updateDirectors(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllDirectors() {
        var listaPeliculas = directorsService.findAll();
        return new ResponseEntity<>(listaPeliculas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDirector(@PathVariable int id){
        var pelicula = directorsService.findById(id);
        if (!pelicula.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListDirectorsForName(@PathVariable String filter){
        var directorsList = directorsService.getListDirectorsForName(filter);
        return new ResponseEntity<>(directorsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDirector(@PathVariable int id){
        var message = directorsService.deleteDirector(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
