package com.library.apilibrary.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.BookDto;
import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.entity.Book;
import com.library.apilibrary.model.entity.Genre;
import com.library.apilibrary.model.entity.Type;
import com.library.apilibrary.repository.BookRepository;
import com.library.apilibrary.repository.GenreRepository;
import com.library.apilibrary.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    @Autowired 
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Object create(BookDto dto) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();

        if (dto.getBookName().length() > 50) {
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setDescription(HttpStatus.UNAUTHORIZED);
            response.setMessage("Name should be 50 characters or below");
            return response;
        }


        Book checkName = bookRepository.findByBookName(dto.getBookName());
        if (checkName != null) {
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setDescription(HttpStatus.UNAUTHORIZED);
            response.setMessage("Book already exist");
            return response;
            
        }


        Genre genre = genreRepository.findById(dto.getGenreId()).get();

        Type type = typeRepository.findById(dto.getTypeId()).get();
        
        Book book = new Book(dto.getBookName(), dto.getBookAuthor(), dto.getBookImage(), dto.getBookDesc(), genre, type);
        bookRepository.save(book);
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(bookRepository.findByBookName(dto.getBookName()));
        return response;
    }

    @Override
    public Object read() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        List<Book> checkBook = bookRepository.findByExist();
        
        if (checkBook.isEmpty()) {
            
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Data not found");
            
            return response;
        }
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(bookRepository.findByExist());
        
        return response;
    }

    @Override
    public Object delete(Long id) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<Book> checkBook = bookRepository.findById(id);
        
        if (checkBook.isEmpty()) {
            
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Book not found");
            
            return response;
        }

        Book book = checkBook.get();
        book.setDeleted(true);
        bookRepository.save(book);

        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Book successfully deleted");
        return response;
    }

    @Override
    public Object update(BookDto dto, Long id) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<Book> checkBook = bookRepository.findById(id);

        if (checkBook.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Book not found");
            
            return response;
        }

        Book book = checkBook.get();
        if (dto.getBookName() != null) {
            book.setBookName(dto.getBookName());;
        }

        if (dto.getBookAuthor() != null) {
            book.setBookAuthor(dto.getBookAuthor());
        }

        if (dto.getBookImage() != null) {
            book.setBookImage(dto.getBookImage());
        }

        if (dto.getBookDesc() != null) {
            book.setBookDesc(dto.getBookDesc());
        }

        if (dto.getGenreId() != null) {

            Genre genre = genreRepository.findById(dto.getGenreId()).get();

            book.setGenreId(genre);
        }

        if (dto.getTypeId() != null) {

            Type type = typeRepository.findById(dto.getTypeId()).get();
            book.setTypeId(type);
        }

        bookRepository.save(book);
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("User successfully updated");
        response.setResult(bookRepository.findById(id));

        return response;
    }

    @Override
    public Object search(BookDto dto) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();

        if (dto.getBookName() != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setDescription(HttpStatus.OK);
            response.setMessage("Book successfully loaded");
            response.setResult(bookRepository.findByBookNameOrderByBookId(dto.getBookName()));
        }

        if (dto.getBookAuthor() != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setDescription(HttpStatus.OK);
            response.setMessage("Book successfully loaded");
            response.setResult(bookRepository.findByBookAuthorContaining(dto.getBookAuthor()));
        }

        if (dto.getGenreId() != null) {

            
            Genre genre = genreRepository.findById(dto.getGenreId()).get();

            response.setStatus(HttpStatus.OK.value());
            response.setDescription(HttpStatus.OK);
            response.setMessage("Book successfully loaded");
            response.setResult(bookRepository.findByGenreId(genre));
        }
        
        // response.setStatus(HttpStatus.OK.value());
        // response.setDescription(HttpStatus.OK);
        // response.setMessage("Book successfully loaded");
        // response.setData(bookRepository.findByBookNameOrderByBookId(dto.getBookName()));
        // response.setData(bookRepository.findByBookAuthorContaining(dto.getBookAuthor()));
        // response.setData(bookRepository.findByGenreIdOrderByBookName(dto.getGenreId()));

        return response;
    }

    @Override
    public Object findByBookId(Long id) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<Book> checkBook = bookRepository.findById(id);
        
        if (checkBook.isEmpty()) {
            
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Data not found");
            
            return response;
        }
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(bookRepository.findById(id));
        
        return response;
    }


}
