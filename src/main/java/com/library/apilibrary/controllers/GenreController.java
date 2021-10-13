package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.GenreDto;
import com.library.apilibrary.service.GenreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {
    
    @Autowired
    GenreServiceImpl genreService;

    @GetMapping
    public ResponseEntity<Object> getBooks() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(genreService.getAll(), HttpStatus.OK);

        return responseEntity;
    }
    
    @PostMapping
    public ResponseEntity<Object> addBook(@ModelAttribute GenreDto dto) {
        //TODO: process POST request
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(genreService.addGenre(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
}
