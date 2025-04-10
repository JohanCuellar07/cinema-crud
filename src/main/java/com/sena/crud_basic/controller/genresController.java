package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.genresDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.genresService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/genres")
public class genresController {
    
    @Autowired
    private genresService genresService;

    @PostMapping("/")
    public ResponseEntity<Object> registerGenres(@RequestBody genresDTO genres){
        responseDTO respuesta = genresService.save(genres);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGenres(@PathVariable int id, @RequestBody genresDTO dto) {
        responseDTO respuesta = genresService.updateGenres(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllGenres() {
        var listaGeneros = genresService.findAll();
        return new ResponseEntity<>(listaGeneros, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneGenre(@PathVariable int id){
        var genero = genresService.findById(id);
        if (!genero.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genero, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListGenresForName(@PathVariable String filter) {
        var genresList = genresService.getListGenresForName(filter);
        return new ResponseEntity<>(genresList, HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable int id){
        var message = genresService.deleteGenre(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
