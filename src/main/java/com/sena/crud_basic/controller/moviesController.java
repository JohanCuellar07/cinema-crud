package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.moviesDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.moviesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class moviesController {
    /*
     * GET
     * POST (REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private moviesService moviesService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovies(@RequestBody moviesDTO movies) {
        responseDTO respuesta = moviesService.save(movies);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovies(@PathVariable int id, @RequestBody moviesDTO dto) {
        responseDTO respuesta = moviesService.updateMovies(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllMovies() {
        var listaPeliculas = moviesService.findAll();
        return new ResponseEntity<>(listaPeliculas, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMovie(@PathVariable int id){
        var pelicula = moviesService.findById(id);
        if (!pelicula.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @GetMapping("/filter/title/{filter}")
    public ResponseEntity<Object> getListMoviesForTitle(@PathVariable String filter){
        var moviesList = moviesService.getListMoviesForTitle(filter);
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
    /* 
    @GetMapping("/filter/launch_year/{filter}")
    public ResponseEntity<Object> getListMoviesForLaunchYear(@PathVariable String filter){
        var moviesList = moviesService.getListMoviesForLaunchYear(filter);
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable int id){
        var message = moviesService.deleteMovie(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
