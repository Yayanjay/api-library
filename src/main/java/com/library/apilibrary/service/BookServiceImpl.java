package com.library.apilibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.BookDto;
import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.entity.Book;
import com.library.apilibrary.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    @Autowired 
    private BookRepository bookRepository;

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


        Book book = new Book(dto.getBookName(), dto.getBookAuthor(), dto.getBookimage(), dto.getBookDesc(), dto.getGenreId());
        bookRepository.save(book);
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(bookRepository.findByBookName(dto.getBookName()));
        System.out.println("response " + response);
        return response;
    }

    @Override
    public Object delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object read() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        List<Book> checkBook = bookRepository.findAll();

        if (checkBook.isEmpty()) {

            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Data not found");
            
            return response;
        }

        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(bookRepository.findAll());

        return response;
    }

    @Override
    public Object update(BookDto dto, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
