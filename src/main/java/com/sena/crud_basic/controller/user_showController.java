package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.user_showDTO;
import com.sena.crud_basic.service.user_showService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user_show")
public class user_showController {
    @Autowired
    private user_showService user_showService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser_Show(@RequestBody user_showDTO user_show) {
        user_showService.save(user_show);
        return new ResponseEntity<>("register Ok", HttpStatus.OK);
    }
    
}
