package com.library.apilibrary.service;

import com.library.apilibrary.model.dto.BookDto;

public interface BookService {
    public Object create(BookDto dto);

    public Object read();

    public Object search(BookDto dto);

    public Object update(BookDto dto, Long id);

    public Object delete(Long id);

    public Object findByBookId(Long id);
}
