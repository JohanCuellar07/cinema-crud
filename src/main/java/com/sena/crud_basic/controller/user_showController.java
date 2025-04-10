package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.user_showDTO;
import com.sena.crud_basic.service.user_showService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user_show")
public class user_showController {
    @Autowired
    private user_showService user_showService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser_Show(@RequestBody user_showDTO user_show) {
        responseDTO respuesta = user_showService.save(user_show);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser_Show(@PathVariable int id, @RequestBody user_showDTO dto) {
        responseDTO respuesta = user_showService.updateUser_Show(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllUser_Show(){
        var listUsuariosFunciones = user_showService.findAll();
        return new ResponseEntity<>(listUsuariosFunciones, HttpStatus.OK);
    }
}
