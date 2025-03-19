package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.movie_genreDTO;
import com.sena.crud_basic.service.movie_genreService;

@RestController
@RequestMapping("/movie_genre")
public class movie_genreController {
    @Autowired
    private movie_genreService movie_genreService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMovie_Genre(@RequestBody movie_genreDTO movie_genre){
        movie_genreService.save(movie_genre);
        return new ResponseEntity<>("register Ok", HttpStatus.OK);
    }
}
