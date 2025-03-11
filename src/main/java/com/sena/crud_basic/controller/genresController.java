package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.genresDTO;

import com.sena.crud_basic.service.genresService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/genres")
public class genresController {
    @Autowired
    private genresService genresService;

    @PostMapping("/")
    public ResponseEntity<Object> registerGenres(@RequestBody genresDTO genres){
        genresService.save(genres);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
}
