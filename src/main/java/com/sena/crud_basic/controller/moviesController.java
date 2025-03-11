
package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.moviesDTO;
import com.sena.crud_basic.service.moviesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/movies")
public abstract class moviesController {
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
        moviesService.save(movies);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}
