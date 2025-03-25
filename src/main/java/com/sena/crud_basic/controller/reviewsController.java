package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.service.reviewsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/reviews")
public class reviewsController {
    
    @Autowired
    private reviewsService reviewsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerReviews(@RequestBody reviewsDTO reviews) {
        responseDTO respuesta = reviewsService.save(reviews);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllReviews() {
        var listaResenas = reviewsService.findAll();
        return new ResponseEntity<>(listaResenas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReview(@PathVariable int id) {
        var resena = reviewsService.findById(id);
        if (!resena.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resena, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable int id){
        var message = reviewsService.deleteReview(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
