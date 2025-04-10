package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.usersDTO;
import com.sena.crud_basic.service.usersService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class usersController {

    @Autowired
    private usersService usersService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUsers(@RequestBody usersDTO users){
        responseDTO respuesta = usersService.save(users);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsers(@PathVariable int id, @RequestBody usersDTO dto) {
        responseDTO respuesta = usersService.updateUsers(id, dto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        var listaUsuarios = usersService.findAll();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }
    
    /*
     * Se requiere un dato, el ID
     * PathVariable = captura de información por la url
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
       var usuario = usersService.findById(id);
       if (!usuario.isPresent()) {
           return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/filter/name/{filter}")
    public ResponseEntity<Object> getListUsersForName(@PathVariable String filter) {
        var usersList = usersService.getListUsersForName(filter);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/filter/lastname/{filter}")
    public ResponseEntity<Object> getListUsersForLastName(@PathVariable String filter) {
        var usersList = usersService.getListUsersForLastName(filter);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/filter/email/{filter}")
    public ResponseEntity<Object> getListUsersForEmail(@PathVariable String filter) {
        var usersList = usersService.getListUsersForEmail(filter);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/filter/phone/{filter}")
    public ResponseEntity<Object> getListUsersForPhone(@PathVariable String filter) {
        var usersList = usersService.getListUsersForPhone(filter);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        var message = usersService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
