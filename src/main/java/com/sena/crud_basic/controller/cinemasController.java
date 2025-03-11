package com.sena.crud_basic.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.cinemasDTO;
import com.sena.crud_basic.service.cinemasService;

@RestController
@RequestMapping("/cinemas")
public class cinemasController {
    @Autowired
    private cinemasService cinemasService;

    @PostMapping("/")
    public ResponseEntity<Object> registerCinemas(@RequestBody cinemasDTO cinemas){
        cinemasService.save(cinemas);
        return new ResponseEntity<>("register OK", HttpStatus.OK)
    }
}
