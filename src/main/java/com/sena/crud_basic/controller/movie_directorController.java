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

import com.sena.crud_basic.DTO.movie_directorDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.movie_diectorService;

@RestController
@RequestMapping("/movie_director")
public class movie_directorController {
    @Autowired
    private movie_diectorService movie_directorService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovie_Director(@RequestBody movie_directorDTO movie_director){
        responseDTO respuesta = movie_directorService.save(movie_director);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie_Director(@PathVariable int id, @RequestBody movie_directorDTO dto) {
        responseDTO respuesta = movie_directorService.updateMovie_Director(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMovie_Director() {
        var listPeliculasDirectores = movie_directorService.findAll();
        return new ResponseEntity<>(listPeliculasDirectores, HttpStatus.OK);
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<Object> deleteByMovieId(@PathVariable int movieId) {
        var message = movie_directorService.deleteByMovieId(movieId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
