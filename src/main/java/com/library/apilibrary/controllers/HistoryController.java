package com.library.apilibrary.controllers;

import com.library.apilibrary.model.dto.HistoryDto;
import com.library.apilibrary.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "http://localhost:8080")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public ResponseEntity<Object> getHistory() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(historyService.read(), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@ModelAttribute HistoryDto dto) {
        //TODO: process POST request
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(historyService.create(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@ModelAttribute HistoryDto dto, @PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(historyService.update(dto, id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }
}
