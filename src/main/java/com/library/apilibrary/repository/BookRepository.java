package com.library.apilibrary.repository;

import java.util.List;

import com.library.apilibrary.model.entity.Book;
import com.library.apilibrary.model.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);
    Book findByGenreId(Genre genreId);
    Book findByBookAuthor(String bookAuthor);
    Book findByBookNameOrderByBookId(String bookName);
    List<Book> findByGenreIdOrderByBookName(Genre genreId);
    List<Book> findByBookAuthorContaining(String bookAuthor);
    
    @Query(value = "SELECT * FROM books WHERE book_name = :bookName OR book_author LIKE %:bookAuthor% OR genre_id = :genreId AND is_deleted = false ORDER BY book_id ASC LIMIT 6", nativeQuery = true)
    List<Book> searchBy(@Param("bookName") String bookName, @Param("bookAuthor") String bookAuthor, @Param("genreId") Long genreId);
}
