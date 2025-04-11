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

import com.sena.crud_basic.DTO.platformsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.platformsService;

@RestController
@RequestMapping("/platforms")
public class platformsController {
    @Autowired
    private platformsService platformsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPlatforms(@RequestBody platformsDTO platforms) {
        responseDTO respuesta = platformsService.save(platforms);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlatforms(@PathVariable int id, @RequestBody platformsDTO dto) {
        responseDTO respuesta = platformsService.updatePlatforms(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPlatforms() {
        var listaPlataformas = platformsService.findAll();
        return new ResponseEntity<>(listaPlataformas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePlatform(@PathVariable int id){
        var plataforma = platformsService.findById(id);
        if (!plataforma.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plataforma, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListPlatformsForName(@PathVariable String filter){
        var platformsList = platformsService.getListPlatformsForName(filter);
        return new ResponseEntity<>(platformsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlatform(@PathVariable int id){
        var message = platformsService.deletePlatform(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
