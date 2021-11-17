package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.TypeDto;
import com.library.apilibrary.service.TypeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@CrossOrigin(origins = "http://localhost:8080")

public class TypeController {

    @Autowired
    TypeServiceImpl typeService;

    @GetMapping
    public ResponseEntity<Object> getType() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(typeService.getAll(), HttpStatus.OK);

        return responseEntity;
    }
    
    @PostMapping
    public ResponseEntity<Object> addGenre(@ModelAttribute TypeDto dto) {
        //TODO: process POST request
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(typeService.addType(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
}
