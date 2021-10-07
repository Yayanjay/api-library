package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.UserDto;
import com.library.apilibrary.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.read(), HttpStatus.OK);

        return responseEntity;
    }
    @PostMapping
    public ResponseEntity<Object> addUser(@ModelAttribute UserDto dto) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.create(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@ModelAttribute UserDto dto, @PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.update(dto, id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteuser(@PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.delete(id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
}
