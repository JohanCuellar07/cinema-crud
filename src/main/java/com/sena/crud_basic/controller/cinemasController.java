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
import com.sena.crud_basic.DTO.cinemasDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.cinemasService;

@RestController
@RequestMapping("/cinemas")
public class cinemasController {
    
    @Autowired
    private cinemasService cinemasService;

    @PostMapping("/")
    public ResponseEntity<Object> registerCinemas(@RequestBody cinemasDTO cinemas){
        responseDTO respuesta = cinemasService.save(cinemas);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCinemas(@PathVariable int id, @RequestBody cinemasDTO dto) {
        responseDTO respuesta = cinemasService.updateCinemas(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCinemas(){
        var listaCines = cinemasService.findAll();
        return new ResponseEntity<>(listaCines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCinema(@PathVariable int id){
        var cine = cinemasService.findById(id);
        if (!cine.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cine, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListCinemasForName(@PathVariable String filter){
        var cinemasList = cinemasService.getListCinemasForName(filter);
        return new ResponseEntity<>(cinemasList, HttpStatus.OK);
    }

    @GetMapping("/filter/address/{filter}")
    public ResponseEntity<Object> getListCinemasForAddress(@PathVariable String filter){
        var cinemasList = cinemasService.getListCinemasForAddress(filter);
        return new ResponseEntity<>(cinemasList, HttpStatus.OK);
    }

    @GetMapping("/filter/phone/{filter}")
    public ResponseEntity<Object> getListCinemasForPhone(@PathVariable String filter){
        var cinemasList = cinemasService.getListCinemasForPhone(filter);
        return new ResponseEntity<>(cinemasList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCinema(@PathVariable int id){
        var message = cinemasService.deleteCinema(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
