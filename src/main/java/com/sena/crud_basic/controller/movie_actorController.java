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

import com.sena.crud_basic.DTO.movie_actorDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.movie_actorService;
@RestController
@RequestMapping("/movie_actor")
public class movie_actorController {
    @Autowired
    private movie_actorService movie_actorService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovie_Actor(@RequestBody movie_actorDTO movie_actor){
        responseDTO respuesta = movie_actorService.save(movie_actor);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie_Actor(@PathVariable int id, @RequestBody movie_actorDTO dto) {
        responseDTO respuesta = movie_actorService.updateMovie_Actor(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMovie_Actor() {
        var listPeliculasActores = movie_actorService.findAll();
        return new ResponseEntity<>(listPeliculasActores, HttpStatus.OK);
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<Object> getByActorId(@PathVariable int actorId) {
        var listPorActores = movie_actorService.findAllByActorId(actorId);
        return new ResponseEntity<>(listPorActores, HttpStatus.OK);
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<Object> deleteByMovieId(@PathVariable int movieId) {
        var message = movie_actorService.deleteByMovieId(movieId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/actor/{actorId}")
    public ResponseEntity<Object> deleteByActorId(@PathVariable int actorId) {
        var message = movie_actorService.deleteByActorId(actorId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
