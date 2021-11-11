package com.library.apilibrary.service;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.GenreDto;
import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.entity.Genre;
import com.library.apilibrary.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    ResponsDto<Object> response = new ResponsDto<>();

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Object addGenre(GenreDto dto) {
        // TODO Auto-generated method stub
        Genre genre = new Genre(dto.getGenre());
        genreRepository.save(genre);

        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(genreRepository.findAll());

        return response;
    }

    @Override
    public Object deleteGenre(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object editGenre(GenreDto dto, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAll() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(genreRepository.findAll());

        return response;
    }

    

    
}
