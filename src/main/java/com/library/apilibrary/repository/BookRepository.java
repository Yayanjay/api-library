package com.library.apilibrary.repository;

import com.library.apilibrary.model.entity.Book;
import com.library.apilibrary.model.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);
    Book findByGenreId(Genre genreId);
}
