package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.showsDTO;

@RestController
@RequestMapping("/shows")
public class showsController {
    @Autowired
    private com.sena.crud_basic.service.showsService showsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerShows(@RequestBody showsDTO shows){
        showsService.save(shows);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
