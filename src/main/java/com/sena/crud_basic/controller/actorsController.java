package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.actorsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.actorsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/actors")
public class actorsController {
    @Autowired
    private actorsService actorsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerActors(@RequestBody actorsDTO actors){
        responseDTO respuesta = actorsService.save(actors);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateActors(@PathVariable int id, @RequestBody actorsDTO dto) {
        responseDTO respuesta = actorsService.updateActors(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllActors() {
        var listaPeliculas = actorsService.findAll();
        return new ResponseEntity<>(listaPeliculas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneActor(@PathVariable int id){
        var pelicula = actorsService.findById(id);
        if (!pelicula.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListActorsForName(@PathVariable String filter){
        var actorsList = actorsService.getListActorsForName(filter);
        return new ResponseEntity<>(actorsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActor(@PathVariable int id){
        var message = actorsService.deleteActor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
