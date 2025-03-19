package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.service.reviewsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reviews")
public class reviewsController {
    
    @Autowired
    private reviewsService reviewsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerReviews(@RequestBody reviewsDTO reviews) {
        reviewsService.save(reviews);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}
