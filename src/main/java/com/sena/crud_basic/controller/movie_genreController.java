package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.movie_genreDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.movie_genreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/movie_genre")
public class movie_genreController {
    @Autowired
    private movie_genreService movie_genreService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovie_Genre(@RequestBody movie_genreDTO movie_genre){
        responseDTO respuesta = movie_genreService.save(movie_genre);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie_Genre(@PathVariable int id, @RequestBody movie_genreDTO dto) {
        responseDTO respuesta = movie_genreService.updateMovie_Genre(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMovie_Genre() {
        var listPeliculasGeneros = movie_genreService.findAll();
        return new ResponseEntity<>(listPeliculasGeneros, HttpStatus.OK);
    }
}
