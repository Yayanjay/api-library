package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.BookDto;
import com.library.apilibrary.service.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<Object> getBooks() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.read(), HttpStatus.OK);

        return responseEntity;
    }
    
    @PostMapping
    public ResponseEntity<Object> addBook(@ModelAttribute BookDto dto) {
        //TODO: process POST request
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.create(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
    
    
}
