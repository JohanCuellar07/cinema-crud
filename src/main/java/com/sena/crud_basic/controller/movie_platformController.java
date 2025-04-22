package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.movie_platformDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.movie_platformService;

@RestController
@RequestMapping("/movie_platform")
public class movie_platformController {
    @Autowired
    private movie_platformService movie_platformService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovie_Platform(@RequestBody movie_platformDTO movie_platform){
        responseDTO respuesta = movie_platformService.save(movie_platform);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie_Platform(@PathVariable int id, @RequestBody movie_platformDTO dto) {
        responseDTO respuesta = movie_platformService.updateMovie_Platform(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMovie_Platform() {
        var listPeliculasPlatforms = movie_platformService.findAll();
        return new ResponseEntity<>(listPeliculasPlatforms, HttpStatus.OK);
    }
}
