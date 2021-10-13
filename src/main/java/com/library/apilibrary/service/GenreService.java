package com.library.apilibrary.service;

import com.library.apilibrary.model.dto.GenreDto;

public interface GenreService  {
    public Object addGenre(GenreDto dto);

    public Object getAll();

    public Object editGenre(GenreDto dto, Long id);

    public Object deleteGenre(Long id);
}
