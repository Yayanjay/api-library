package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.BookDto;
import com.library.apilibrary.service.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<Object> getBooks() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.read(), HttpStatus.OK);

        return responseEntity;
    }
    
    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody BookDto dto) {
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


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody BookDto dto, @PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.update(dto, id), HttpStatus.OK);
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
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.delete(id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> findByBookId(@PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.findByBookId(id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }


    @PostMapping("/search")
    public ResponseEntity<Object> search(@RequestBody BookDto dto) {
        //TODO: process POST request
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(bookService.search(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
    
    
}
